package org.yaukie.helper;

import org.yaukie.util.ObjectUtil;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaukie.constant.WebApiConfig;

/**
 *  @Author: yuenbin
 *  @Date :2020/5/15
 * @Time :10:32
 * @Motto: It is better to be clear than to be clever !
 * @Destrib:  发布webservice
**/
public class PublishWebServiceHelper {
	
	private static final Logger log = LoggerFactory.getLogger(PublishWebServiceHelper.class);
	
	private static final List<Interceptor< ? extends Message>> inInterceptorList = new ArrayList<Interceptor <? extends Message>>();
	private static final List<Interceptor< ? extends Message>> outInterceptorList = new ArrayList<Interceptor <? extends Message>>();

	static {
		
			if(WebApiConfig.isWsLog()){
				//添加WebApi 服务调用日志
				LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
				inInterceptorList.add(loggingInInterceptor);
				LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
				outInterceptorList.add(loggingOutInterceptor);
			}
	}
	
	public static void publishWebService(String servicePath ,Class<?>  interClass ){
		if(log.isDebugEnabled()){
			log.debug("开始创建web service 服务[ "+interClass.getName()+"]");
		}
	
		ServerFactoryBean factory = new  ServerFactoryBean();
		factory.setAddress(servicePath);
		factory.setServiceClass(interClass);
		Class<?> implClass = ClassHelper.getClassListBySuper(interClass).get(0);
		
		log.debug("获取到的实现类为:"+implClass.getSimpleName()); 
		
		factory.setServiceBean(ObjectUtil.newInstance(implClass.getName()));
		factory.setInInterceptors(inInterceptorList);
		factory.setOutInterceptors(outInterceptorList);
		factory.create();
		if(log.isDebugEnabled()){
			log.debug("web service 服务[ "+interClass.getName()+"]创建成功 !");
		}
	
}
	
	public static Object createWebClient(String servicePath ,Class<? extends Object> interClass){
		ClientProxyFactoryBean cpfb = new ClientProxyFactoryBean();
		cpfb.setAddress(servicePath);
		cpfb.setInInterceptors(inInterceptorList);
		cpfb.setOutInterceptors(outInterceptorList);
		cpfb.setServiceClass(interClass);
		return cpfb.create(interClass);
	}
	
}
