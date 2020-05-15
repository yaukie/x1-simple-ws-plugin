package org.yaukie.support;

import org.yaukie.support.api.ClassSupportApi;
import org.yaukie.support.base.AnnotationByBaseClass;
import org.yaukie.support.base.BaseClass;
import org.yaukie.support.base.SuperByBaseClass;

import java.lang.annotation.Annotation;


import java.util.List;

/**
 * 扫描基础包下的指定类
 * @author yebn
 *
 */
public class ClassSupportImpl implements ClassSupportApi {

	@Override
	public List<Class<?>> getClassList(String packageName) {
		return new BaseClass(packageName) {
			@Override
			public boolean checkAddClass(Class<?> cls) {

                String className = cls.getName();
                String pkgName = className.substring(0, className.lastIndexOf("."));
                return pkgName.startsWith(packageName);
                
			}
		}.getClassList();
	}

	@Override
	public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
		return new AnnotationByBaseClass(packageName,annotationClass) {
			
			@Override
			public boolean checkAddClass(Class<?> cls) {
				return cls.isAnnotationPresent((Class<? extends Annotation>) annotationClass);
			}
		}.getClassList();
	}

	@Override
	public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
			return new SuperByBaseClass(packageName,superClass) {
				
				@Override
				public boolean checkAddClass(Class<?> cls) {
					
					return superClass.isAssignableFrom(cls) && !cls.equals(superClass);
				}
			}.getClassList();
	}
 
	
	
}
