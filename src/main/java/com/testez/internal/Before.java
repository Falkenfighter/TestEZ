package com.testez.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/14/2015
 */
public abstract class Before implements RunnableInstance {
    private final Annotation[] annotations;

    public Before(AnnotatedElement element) {
        this.annotations = element.getAnnotations();
    }
}
