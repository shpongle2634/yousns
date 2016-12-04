package com.yousns.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //for Reflction
@Target(ElementType.PARAMETER)//using parameter
public @interface RequestParam {
	public String value();
}
