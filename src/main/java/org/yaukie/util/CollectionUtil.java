package org.yaukie.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;


/**
 *  @Author: yuenbin
 *  @Date :2020/5/15
 * @Time :10:17
 * @Motto: It is better to be clear than to be clever !
 * @Destrib: 集合操作类
**/
public class CollectionUtil {

	/**
	 * 判断集合是否为空
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection)
	{
		return CollectionUtils.isEmpty(collection);
	}
	
	
	/**
	 * 判断集合是否为非空
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> collection)
	{
		return !CollectionUtils.isEmpty(collection);
	}
	
	
	/**
	 * 判断Map是否为空
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<?,?> map)
	{
		return MapUtils.isEmpty(map);
	}
	
	
	/**
	 * 判断Map是否为非空
	 * @param map
	 * @return
	 */
	public static boolean isNotEmpty(Map<?,?> map)
	{
		return !MapUtils.isEmpty(map);
	}
	
	public static void main(String[] args) {
		System.out.println("dd");
	}
	
}
