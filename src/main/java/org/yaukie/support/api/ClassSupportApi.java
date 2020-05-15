package org.yaukie.support.api;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 *  @Author: yuenbin
 *  @Date :2020/5/15
 * @Time :10:52
 * @Motto: It is better to be clear than to be clever !
 * @Destrib:  class 对外提供支持类
**/
public interface ClassSupportApi {

    /**
     * 获取指定包名中的所有类
     */
    List<Class<?>> getClassList(String packageName);

    /**
     * 获取指定包名中指定注解的相关类
     */
    List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass);

    /**
     * 获取指定包名中指定父类或接口的相关类
     */
    List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass);
	
	
}
