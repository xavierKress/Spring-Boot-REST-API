package com.example.spring_boot_rest_api.aspects;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExecutionTimeMonitoring {
	String operation();
}
