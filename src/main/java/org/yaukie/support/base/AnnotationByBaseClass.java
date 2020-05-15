package org.yaukie.support.base;

public abstract class AnnotationByBaseClass extends BaseClass{

	protected final Class<?> annotationClass; 
	
	protected AnnotationByBaseClass(String packageName,Class<?> annotationClass) {
		super(packageName);
		this.annotationClass=annotationClass;
	}

}
