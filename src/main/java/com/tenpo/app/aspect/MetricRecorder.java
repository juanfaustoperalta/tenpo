package com.tenpo.app.aspect;

import com.tenpo.app.model.TransactionName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MetricRecorder {

	TransactionName name();

}