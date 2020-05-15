package org.yaukie.helper;

import org.yaukie.util.ObjectUtil;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.provider.jsonp.JsonpInInterceptor;
import org.apache.cxf.jaxrs.provider.jsonp.JsonpPostStreamInterceptor;
import org.apache.cxf.jaxrs.provider.jsonp.JsonpPreStreamInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaukie.constant.WebApiConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 *  @Author: yuenbin
 *  @Date :2020/5/15
 * @Time :10:32
 * @Motto: It is better to be clear than to be clever !
 * @Destrib:  发布rest服务
**/
public class PublishRestServiceHelper {
	
	private static final Logger log = LoggerFactory.getLogger(PublishRestServiceHelper.class);
	   //添加provider  
    private static final List<Object> providerList = new ArrayList<Object>();  
	private static final List<Interceptor< ? extends Message>> inInterceptorList = new ArrayList<Interceptor <? extends Message>>();
	private static final List<Interceptor< ? extends Message>> outInterceptorList = new ArrayList<Interceptor <? extends Message>>();
	
	static {
	    providerList.add( new JacksonJsonProvider());
		/**开启rest服务调用日志*/
		if(WebApiConfig.isRestLog()){
			LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
			inInterceptorList.add(loggingInInterceptor);
			LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
			outInterceptorList.add(loggingOutInterceptor);
		}
		
	      // 添加 JSONP Interceptor
        if (WebApiConfig.isJsonp()) {
            JsonpInInterceptor jsonpInInterceptor = new JsonpInInterceptor();
            jsonpInInterceptor.setCallbackParam(WebApiConfig.getJsonpFunction());
            inInterceptorList.add(jsonpInInterceptor);
            JsonpPreStreamInterceptor jsonpPreStreamInterceptor = new JsonpPreStreamInterceptor();
            outInterceptorList.add(jsonpPreStreamInterceptor);
            JsonpPostStreamInterceptor jsonpPostStreamInterceptor = new JsonpPostStreamInterceptor();
            outInterceptorList.add(jsonpPostStreamInterceptor);
        }
        // 添加 CORS Provider
        if (WebApiConfig.isCors()) {
            CrossOriginResourceSharingFilter corsProvider = new CrossOriginResourceSharingFilter();
            corsProvider.setAllowOrigins(WebApiConfig.getCorsOriginList());
            providerList.add(corsProvider);
        }
		
	}
	
	public static void publishRestService(String baseAddress ,Class<?> resourceClass){
		if(log.isDebugEnabled()){
			log.debug("开始创建restful 服务["+resourceClass.getName()+"]");
		}
        // 添加ResourceClass  
        List<Class<?>> resourceClassList = new ArrayList<Class<?>>();  
        resourceClassList.add(resourceClass); 
          
        //发布REST任务  
        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();  
        factory.setAddress(baseAddress);  
        factory.setResourceClasses(resourceClassList);  
        factory.setInInterceptors(inInterceptorList);
        factory.setOutInterceptors(outInterceptorList);
        factory.setResourceProvider(resourceClass, new SingletonResourceProvider(ObjectUtil.newInstance(resourceClass.getName())));
        factory.setProviders(providerList);  
        factory.create();  
		if(log.isDebugEnabled()){
			log.debug("restful 服务["+resourceClass.getName()+"]创建成功!");
		}
	}
	
	public static Object publishRestClient(String baseAddress,Class< ? extends Object> resourceClass){
		return  JAXRSClientFactory.create(baseAddress, resourceClass);
	}
	
	
}
