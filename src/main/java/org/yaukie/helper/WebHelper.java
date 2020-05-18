package org.yaukie.helper;

import java.util.Properties;

import org.yaukie.constant.WebApiConstant;
import org.yaukie.util.PropsUtil;


/**
 *  @Author: yuenbin
 *  @Date :2020/5/15
 * @Time :10:12
 * @Motto: It is better to be clear than to be clever !
 * @Destrib:  助手类
**/
public final class WebHelper {
	
	private static    Properties PROP = PropsUtil.loadProps(WebApiConstant.CONFIG_FILE);
	
	/**
	 * 获取应用基础包名
	 * @return
	 */
	public static String getAppBasePackage()
	{
		return PropsUtil.getString(PROP, WebApiConstant.APP_PACKAGE);
	}
 
 
	public static String getString(String key){
		return PropsUtil.getString(PROP, key);
	}
	
	public static int getInt(String key){
		return PropsUtil.getInt(PROP, key);
	}
	
	public static boolean getBoolean(String key){
		return PropsUtil.getBoolean(PROP, key);
	}

}
