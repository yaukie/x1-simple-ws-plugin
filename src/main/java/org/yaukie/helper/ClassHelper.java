package org.yaukie.helper;

import java.lang.annotation.Annotation;
import java.util.List;

import org.yaukie.ClassCacheFactory;
import org.yaukie.support.api.ClassSupportApi;


/**
 *  @Author: yuenbin
 *  @Date :2020/5/15
 * @Time :10:55
 * @Motto: It is better to be clear than to be clever !
 * @Destrib: 获取指定应用包名下所有bean类
 *  注意:所谓bean类 指的是被service controller 注解的类
**/
public final class ClassHelper {

    /**
     * 获取基础包名
     */
    private static final String basePackage = WebHelper.getAppBasePackage();

    /**
     * 获取 ClassScanner
     */
    private static final ClassSupportApi  classSupportApi = ClassCacheFactory.getClassSupportApi();

    /**
     * 获取基础包名中的所有类
     */
    public static List<Class<?>> getClassList() {
        return classSupportApi.getClassList(basePackage);
    }

    /**
     * 获取基础包名中指定父类或接口的相关类
     */
    public static List<Class<?>> getClassListBySuper(Class<?> superClass) {
        return classSupportApi.getClassListBySuper(basePackage, superClass);
    }

    /**
     * 获取基础包名中指定注解的相关类
     */
    public static List<Class<?>> getClassListByAnnotation(Class<? extends Annotation> annotationClass) {
        return classSupportApi.getClassListByAnnotation(basePackage, annotationClass);
    }
}
