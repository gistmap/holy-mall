package com.gistmap.common.exception.handler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>todo</p>
 *
 * @author zhaoyong
 * @version $Id: PropertyNaming , v 0.1  K555 Exp $
 * @date 2018年07月18 15:50
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyNaming {

}
