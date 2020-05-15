package org.yaukie.util;

import java.io.File;
import java.io.FileFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  @Author: yuenbin
 *  @Date :2020/5/15
 * @Time :10:13
 * @Motto: It is better to be clear than to be clever !
 * @Destrib:  类加载器工具
**/
public final class ClassUtil {

	private static final Logger log = LoggerFactory.getLogger(ClassUtil.class);
	
	/**
	 * 获取类加载器
	 * 当前线程的类加载器
	 * @return
	 */
	public static ClassLoader getClassLoader(){
		return  Thread.currentThread().getContextClassLoader();
	}
	
	/**
	 * 根据类的名称
	 * 获取类
	 * @param className
	 * @param isInitialized
	 * @return
	 */
	public static Class<?> loadClass(String className,boolean isInitialized )
	{
		Class cls = null ;
		try {
			cls = Class.forName(className, isInitialized, getClassLoader());
		} catch (ClassNotFoundException e) {
			if(log.isErrorEnabled())
			{
				log.error("load class error .......");
				throw new RuntimeException(e);
			}
		}//isInitialized 表示是否执行该类的静态代码块
		return cls;
	}

	/**
	 * 获取类文件
	 * @param packagePath
	 * @return
	 */
     public  static File[] getClassFiles(String packagePath)
    {
		File[] files = new File(packagePath).listFiles(new FileFilter(){

			@Override
			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(".class") || file.isDirectory());
			}
		});
		return files;
    }

	/**
	 * 获取类名
	 * @param packageName
	 * @param fileName
	 * @return
	 */
    public static String getClassName(String packageName, String fileName) {
        String className = fileName.substring(0, fileName.lastIndexOf("."));
        if (StringUtil.isNotEmpty(packageName)) {
            className = packageName + "." + className;
        }
        return className;
    }

	/**
	 * 获取包路径
	 * @param packagePath
	 * @param filePath
	 * @return
	 */
    public static String getSubPackagePath(String packagePath, String filePath) {
        String subPackagePath = filePath;
        if (StringUtil.isNotEmpty(packagePath)) {
            subPackagePath = packagePath + "/" + subPackagePath;
        }
        return subPackagePath;
    }

	/**
	 * 获取子目录包路径名
	 * @param packageName
	 * @param filePath
	 * @return
	 */
    public static String getSubPackageName(String packageName, String filePath) {
        String subPackageName = filePath;
        if (StringUtil.isNotEmpty(packageName)) {
            subPackageName = packageName + "." + subPackageName;
        }
        return subPackageName;
    }
 
}
