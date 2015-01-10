package com.testez.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 12/30/2014
 */

@Retention(RUNTIME)
@Target({METHOD, TYPE, FIELD, CONSTRUCTOR})
public @interface Test {
    public String[] dependsOn() default {};
    public boolean saveValue() default false;

    Class<? extends Throwable>[] shouldThrow() default {};
}
