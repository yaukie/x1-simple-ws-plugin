package org.yaukie.constant;

/**
 *  @Author: yuenbin
 *  @Date :2020/5/15
 * @Time :10:09
 * @Motto: It is better to be clear than to be clever !
 * @Destrib: 定义需要用到的常量参数
**/
public interface WebApiConstant {

	String CONFIG_FILE = "web.properties";
	
	String APP_PACKAGE="x1.app.base.package";//应用的基础包名

 
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

}
