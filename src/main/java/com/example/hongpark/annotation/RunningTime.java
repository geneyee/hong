package com.example.hongpark.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD}) // 어노테이션 적용 대상 지정
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 유지기간(runtime 시점까지 유지)
public @interface RunningTime {
}
