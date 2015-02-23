package com.testez.annotations;

import com.testez.internal.Invocation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.testez.internal.Invocation.ALL;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/14/2015
 */
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface After {
    Invocation value() default ALL;
    String[] tests() default {};
}
