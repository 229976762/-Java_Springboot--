package com.ghy.server.aop;

import java.lang.annotation.*;
/**
 *
 * @author GHY
 * @date 2023/11/21
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AopLogger {
}
