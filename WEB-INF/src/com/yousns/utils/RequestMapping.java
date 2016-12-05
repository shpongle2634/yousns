package com.yousns.utils;


/*
 * Created 2016-11-17
 * Tae hoon Seo
 * */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //for Reflction
public @interface RequestMapping {
	public String value() default ""; //API를 분기할 url Path
	public String method() default HTTPMETHOD.GET; //RESTful API Method Type
}
