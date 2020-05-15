package org.yaukie.constant;

 import org.yaukie.helper.WebHelper;

 import java.util.Arrays;
import java.util.List;


/**
 *  @Author: yuenbin
 *  @Date :2020/5/15
 * @Time :10:06
 * @Motto: It is better to be clear than to be clever !
 * @Destrib: WebApi接口定义常量配置文件
**/
public class WebApiConfig {
	

	public static boolean isWsLog(){
		return WebHelper.getBoolean(WebApiConstant.WS_LOG);
	}
	

	public static boolean isRestLog(){
		return WebHelper.getBoolean(WebApiConstant.REST_LOG);
	}
		
    public static boolean isJsonp() {
        return WebHelper.getBoolean(WebApiConstant.JSONP);
    }

    public static String getJsonpFunction() {
        return WebHelper.getString(WebApiConstant.JSONP_FUNCTION);
    }

    public static boolean isCors() {
        return WebHelper.getBoolean(WebApiConstant.CORS);
    }

    public static List<String> getCorsOriginList() {
        String corsOrigin = WebHelper.getString(WebApiConstant.CORS_ORIGIN);
        return Arrays.asList(corsOrigin.split(","));
    }
	

	
}
