package org.yaukie;

import org.yaukie.helper.WebHelper;
import org.yaukie.support.api.ClassSupportApi;
import org.yaukie.util.ClassUtil;
import org.yaukie.util.ObjectUtil;
import org.yaukie.util.StringUtil;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: yuenbin
 * @Date :2020/5/15
 * @Time :10:56
 * @Motto: It is better to be clear than to be clever !
 * @Destrib: 构建class访问缓存区，目的是避免频繁
 * 扫描包，减少不必要的操作
 **/
public class ClassCacheFactory {


    public static ConcurrentHashMap<String, Object> cacheMap = new ConcurrentHashMap();

    public static final String CLASS_SUPPORT_CACHE_KEY = "x1_class_support_api_key";


    public static final String defaultClassSupportApi = "org.yaukie.support.ClassSupportImpl";

    /**
     * 通过反射获取真实
     * 实现类
     *
     * @return
     */
    public static ClassSupportApi getClassSupportApi() {
        return (ClassSupportApi) getInstance(
                CLASS_SUPPORT_CACHE_KEY,
                defaultClassSupportApi);
    }

    /**
     * 取得实例方法
     * 先从缓存取，如果之前没加载过，
     * 则从加载区获取，然后放入缓存中
     * 方便取用,减少资源开支
     *
     * @param cacheKey
     * @return
     */
    private static Object getInstance(String cacheKey, String defaultClassName) {

        Object resultObj = null;

        resultObj = cacheMap.get(cacheKey);

        if (resultObj == null) {

            String classImpl = WebHelper.getString(cacheKey);
            if (StringUtil.isEmpty(classImpl)) {
                classImpl = defaultClassName;
            }
            resultObj = ObjectUtil.newInstance(classImpl);
            if (resultObj != null) {
                cacheMap.put(cacheKey, resultObj);
            }

        }

        return resultObj;

    }


}
