package org.yaukie.support.base;

public abstract class SuperByBaseClass extends BaseClass{

	protected final Class<?> superClass; 
	
	protected SuperByBaseClass(String packageName,Class<?> superClass) {
		super(packageName);
		this.superClass=superClass;
	}

}
