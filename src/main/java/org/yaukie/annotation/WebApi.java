package org.yaukie.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: yuenbin
 * @Date :2020/5/15
 * @Time :9:56
 * @Motto: It is better to be clear than to be clever !
 * @Destrib: 主要用于使用注解来标识该服务类型
 * webservice服务还是rest服务，减少配置工作量，屏蔽底层操作
 * 提升编程效率
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebApi {
    /**
     * 属性值 默认为空,指定服务请求上下文
     */
    String serverPath() default "";

    /**
     * 默认标识为webservice
     *
     * @return
     */
    public   Type serviceType() default Type.WS;

    /**
     * WS 标识 webService
     * REST 标识 restful
     */
    enum Type {
        WS, REST
    }


}
