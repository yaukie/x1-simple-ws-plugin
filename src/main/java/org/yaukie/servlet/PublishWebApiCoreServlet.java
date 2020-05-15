package org.yaukie.servlet;

import org.yaukie.annotation.WebApi;
import org.yaukie.helper.ClassHelper;
import org.yaukie.helper.PublishRestServiceHelper;
import org.yaukie.helper.PublishWebServiceHelper;
import org.yaukie.util.CollectionUtil;
import org.yaukie.util.StringUtil;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  @Author: yuenbin
 *  @Date :2020/5/15
 * @Time :10:19
 * @Motto: It is better to be clear than to be clever !
 * @Destrib:  发布ws服务，rest服务核心步骤，容器启动
 * 的时候，自动发布对应服务
**/
@WebServlet(loadOnStartup = 1,urlPatterns = "/ws/*")
public class PublishWebApiCoreServlet extends CXFNonSpringServlet{
	  private static final long serialVersionUID = 1L;  
	  private static final Logger log = LoggerFactory.getLogger(PublishWebApiCoreServlet.class);
	  
	  /**
	   * 使用cxf环境发布ws服务,脱离spring,
	   * 简单粗暴
	   */
	  @Override
	protected void loadBus(ServletConfig sc) {
		  super.loadBus(sc);
		  Bus bus = this.getBus();
		  BusFactory.setDefaultBus(bus);
		  /**发布ws服务*/
		  publishService();
		  
	}
	  
	 /**
	  * 发布带有注解WebApi注解的web 服务
	  * 包括 ws 服务, rest 服务
	  */
	  private static void publishService(){
		  Set<Class<?>> targetClassSet = new HashSet<Class<?>>(ClassHelper.getClassListByAnnotation(WebApi.class));
		  if(CollectionUtil.isNotEmpty(targetClassSet)){
			  for(Class<?>  cls : targetClassSet ){
				  WebApi webApi = cls.getAnnotation(WebApi.class);
				  /*获取被注解的服务类型*/
				  WebApi.Type type = webApi.serviceType();
				  //获取被注解的服务地址
				  String servicePath = webApi.serverPath();
				  //发布 webservice 服务
				  if(type != null && type.equals(WebApi.Type.WS)){
					  //如果是接口,则进行ws发布
					  if(cls.isInterface()){
						  PublishWebServiceHelper.publishWebService(getServiceAddress(type,cls,servicePath), cls);
					  }
					
					  //发布 rest服务
				  }else if(type !=null && type.equals(WebApi.Type.REST)){
					  PublishRestServiceHelper.publishRestService(getServiceAddress(type,cls,servicePath),cls);
				  }
				
			  }
		  }
	  }
	  

	  
	  /**
	   * 获取注解的服务地址
	   * 服务地址应该是 /ws/*
	   * @param servicePath
	   * @return
	   */
	  private static String getServiceAddress(WebApi.Type type,Class<?> cls ,String servicePath ){
		   String  address = "";
		   
		  if(StringUtil.isEmpty(servicePath)){
				  //类名作为服务地址
				  address=cls.getSimpleName();
		  }else {
			  address=servicePath;
		  }
		  
		  if(!address.startsWith("/")){
			  address = "/"+address;
		  } 
		  address= address.replaceAll("\\/", "/");
		  
		  return address;
	  }
	
}
