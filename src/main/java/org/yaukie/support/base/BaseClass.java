package org.yaukie.support.base;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaukie.util.ClassUtil;

/**
 *  @Author: yuenbin
 *  @Date :2020/5/15
 * @Time :10:43
 * @Motto: It is better to be clear than to be clever !
 * @Destrib:  基础类扫描工具
**/
public abstract class BaseClass {

    private static final Logger log = LoggerFactory.getLogger(BaseClass.class);

    protected final String packageName;

    protected BaseClass(String packageName) {
        this.packageName = packageName;
    }

    public final List<Class<?>> getClassList() {

        List<Class<?>> List = new ArrayList();
        Enumeration<URL> urls;
        try {
            urls = ClassUtil.getClassLoader().getResources(packageName.replaceAll("\\.", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        addClass(List, packagePath, packageName);
                    } else {
                        JarURLConnection jarUrl = (JarURLConnection) url.openConnection();
                        if (jarUrl != null) {
                            JarFile jarFile = jarUrl.getJarFile();
                            Enumeration<JarEntry> jarEntries = jarFile.entries();
                            while (jarEntries.hasMoreElements()) {
                                JarEntry je = jarEntries.nextElement();
                                String jarEntryName = je.getName();
                                if (jarEntryName.endsWith(".class")) {
                                    String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                    doAddClass(List, className);
                                }
                            }
                        }
                    }
                }

            }
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("get class List failure ....", e);
                throw new RuntimeException(e);
            }
        }
        return List;

    }


    /**
     * 根据包路径,
     * 包名添加类
     *
     * @param classList
     * @param packagePath
     * @param packageName
     */
    private void addClass(List<Class<?>> classList, String packagePath, String packageName) {
        //根据包路径列出下面所有的文件
        File[] files = ClassUtil.getClassFiles(packagePath);

        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = ClassUtil.getClassName(packageName, fileName);
                doAddClass(classList, className);
            } else {
                String subPackagePath = ClassUtil.getSubPackagePath(packagePath, fileName);
                String subPackageName = ClassUtil.getSubPackageName(packageName, fileName);
                addClass(classList, subPackagePath, subPackageName);
            }

        }
    }

    /**
     * 添加类的具体实现
     *
     * @param classList
     * @param className
     */
    private void doAddClass(List<Class<?>> classList, String className) {
        Class<?> cls = ClassUtil.loadClass(className, false);
        if (checkAddClass(cls)) {
            classList.add(cls);
        }

    }


    /**
     * 验证是否允许添加类
     */
    public abstract boolean checkAddClass(Class<?> cls);
}
