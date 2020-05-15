package org.yaukie.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  @Author: yuenbin
 *  @Date :2020/5/15
 * @Time :10:18
 * @Motto: It is better to be clear than to be clever !
 * @Destrib: 类对象操作类
**/
public class ObjectUtil {

	private static final Logger log = LoggerFactory.getLogger(ObjectUtil.class);
	
	/**
	 * 通过反射创建实例
	 * @param className
	 * @return
	 */
	public static  Object  newInstance(String className ){
		Object  instance  ; 
		Class<?> cls = ClassUtil.loadClass(className, false);
		try {
			instance = (Object) cls.newInstance();
		} catch (Exception e ){
			log.error("实例化对象失败！");
			throw new RuntimeException(e);
		}
		return instance ; 
	}
	
	
	 
}
