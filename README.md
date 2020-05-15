# 项目简介  

## 项目背景   
   在做项目的过程中，尤其是基于老项目架构的系统，系统本身体量就很大了，系统之间的组件交互避免不了需要用到webservice服务，    
     以及rest服务来通信，这就牵扯到一个问题就是服务的发布。  
     传统的（servlet2.5时代），最经典的，我们需要在WEB-INF/目录下面，编写web.xml文件，然后配置ws服务，之后再去编写servlet类，  
     指定上下文，指定请求路径，还需要编写ws服务的映射文件，指定该服务是一个webservice，还是一个rest服务，因此，比较繁琐，  
     配置文件一旦多了，编写的成本就会增加，出错的几率也会频频攀升，而且还给应用加载带来一定的压力，因此，是时候需要给web服务  
     的发布减减压了。  
     本项目是基于servlet3.0时代，依托于CXF框架，自行封装的同时支持webservice服务以及restful服务的插件，您可以不用编写任何的服务  
     映射文件，甚至不需要配置web.xml，只需关注业务，编写好业务服务，启动tomcat或jetty，就可以像发布微服务一样发布一个webservice  
     或者发布一个rest服务，易用性大大提高，真正做到轻量级。  
     该插件甚至脱离开了spring的框架，使得服务的发布真正做到动态隔离，组件隔离，再也不用编写一大堆的spring配置文件来发布ws服务了。  

## CXF 框架介绍
1、CXF框架概念介绍
        　　Apache CXF 是一个开源的 WebService 框架，CXF可以用来构建和开发 WebService，这些服务可以支持多种协议，  
        比如：SOAP、POST/HTTP、HTTP ，CXF 大大简化了WebService并且可以天然地和 Spring 进行无缝集成。CXF是 Celtrix  
         （ESB框架）和 XFire（webserivice） 合并而成，核心是org.apache.cxf.Bus(总线)，类似于Spring的 ApplicationContext，  
         CXF默认是依赖于Spring的，另 CXF 发行包中的jar，如果全部放到lib中，需要 JDK1.6 及以上，否则会报JAX-WS版本不一致的问题。  
         CXF 内置了Jetty服务器 ，它是servlet容器。  
        
2、CXF框架特点
  A、与Spring、Servlet做了无缝对接，cxf框架里面集成了Servlet容器Jetty
        
  B、支持注解的方式来发布webservice
        
  C、能够显示一个webservice的服务列表
        
  D、能够添加拦截器：输入拦截器、输出拦截器 ：输入日志信息拦截器、输出日志拦截器、用户权限认证的拦截器  
            
3、功能特性  
            CXF 包含了大量的功能特性，但是主要集中在以下几个方面：  
            
   支持 Web Services 标准：CXF 支持多种 Web Services 标准，包含 SOAP、Basic Profile、WS-Addressing、WS-Policy、  
   WS-ReliableMessaging 和 WS-Security。
   Frontends：CXF 支持多种“Frontend”编程模型，CXF 实现了 JAX-WS API （遵循 JAX-WS 2.0 TCK 版本），它也包含一  
   个“simple frontend”允许客户端和 EndPoint 的创建，而不需要 Annotation 注解。CXF 既支持 WSDL 优先开发，也支持从  
   Java 的代码优先开发模式。
   容易使用： CXF 设计得更加直观与容易使用。有大量简单的 API 用来快速地构建代码优先的 Services，各种 Maven 的插  
   件也使集成更加容易，支持 JAX-WS API ，支持 Spring 2.0 更加简化的 XML 配置方式，等等。
   支持二进制和遗留协议：CXF 的设计是一种可插拨的架构，既可以支持 XML ，也可以支持非 XML 的类型绑定，比如：JSON   
   和 CORBA。
       
## 解决的问题  

 1、本项目是基于servlet3.0时代，依托于CXF框架，自行封装的同时支持webservice服务以及restful服务的插件，您可以不用编写任何的服务  
             映射文件，甚至不需要配置web.xml，只需关注业务，编写好业务服务，启动tomcat或jetty，就可以像发布微服务一样发布一个webservice  
             或者发布一个rest服务，易用性大大提高，真正做到轻量级。  
            2、 该项目甚至摆脱开了spring的框架，使得服务的发布真正做到动态隔离，组件隔离，再也不用编写一大堆的spring配置文件来发布ws服务了。  
            3、性能提升，速度提高，维护容易，发布简单。
            
##  轻量级WS框架

- 支持基于WebApi协议的web service,以及restful风格的服务发布  
- 基于 Servlet 3.0 规范  
- 使用 Java 注解取代 XML 配置  

## 主要功能
-- 将Webservice,Rest服务整合到一起,方便配置  
-- 统一发布,统一管理  
-- 使用注解,屏蔽底层  
-- 与业务完全解耦  
-- 零配置,方便集成  

## 项目结构  
           
        ├─src
        │  └─main
        │      ├─java
        │      │  └─org
        │      │      └─yaukie
        │      │          │  ClassCacheFactory.java（项目总入口，加了缓存，提升性能）
        │      │          │  
        │      │          ├─annotation
        │      │          │      WebApi.java（注解核心）
        │      │          │      
        │      │          ├─constant
        │      │          │      WebApiConfig.java（配置核心）
        │      │          │      WebApiConstant.java（配置核心）
        │      │          │      
        │      │          ├─helper
        │      │          │      ClassHelper.java（类加载器）
        │      │          │      PublishRestServiceHelper.java（webservice发布核心）
        │      │          │      PublishWebServiceHelper.java（restful发布核心）
        │      │          │      WebHelper.java
        │      │          │      
        │      │          ├─servlet
        │      │          │      PublishWebApiCoreServlet.java（发布启动核心）
        │      │          │      
        │      │          ├─support
        │      │          │  │  ClassSupportImpl.java
        │      │          │  │  
        │      │          │  ├─api
        │      │          │  │      ClassSupportApi.java
        │      │          │  │      
        │      │          │  └─base
        │      │          │          AnnotationByBaseClass.java
        │      │          │          BaseClass.java
        │      │          │          SuperByBaseClass.java
        │      │          │          
        │      │          └─util
        │      │                  CastUtil.java
        │      │                  ClassUtil.java
        │      │                  CollectionUtil.java
        │      │                  ObjectUtil.java
        │      │                  PropsUtil.java
        │      │                  StringUtil.java
       
	
## 使用方法  
    -- 1、检出地址：http://open.inspur.com/yuenbin/x1-simple-ws-plugin.git  
    -- 2、pom引用（默认maven项目），将该项目的maven三维坐标（groupId,artifactId,version）引入到目标项目中  
    -- 3、在目标项目，请创建一个web.properties文件，在这里至少指定一个参数：x1.app.base.package=，这个参数   
    指定您编写的webservice服务，rest服务接口类所在的目录，方便插件去加载，发布服务，这样您就可以启动项目  
    使用所发布的服务了。  
    -- 4、其他配置说明：  
    
	/**定义是否启用调用服务日志监控*/
    String WS_LOG = "x1.ws.plugin.log";  
	
	/**定义是否启用调用服务日志监控*/
	String REST_LOG = "x1.rest.plugin.log";  
	
	/*是否支持跨域请求*/
	String JSONP = "x1.web.plugin.rest.jsonp";  
	
	 /**设定跨域回调函数名称*/
	String JSONP_FUNCTION = "x1.web.plugin.rest.jsonp.function";  
	
	/*是否开启跨域请求*/
	String CORS = "x1.web.plugin.rest.cors";  
	
	/**服务端允许访问的域名,.用,隔开*/
	String CORS_ORIGIN = "x1.web.plugin.rest.cors.origin";  
    -- 5、案例使用  
    略
    
    

