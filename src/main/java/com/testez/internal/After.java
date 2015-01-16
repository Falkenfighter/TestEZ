package com.testez.internal;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/14/2015
 */
public abstract class After implements RunnableInstance {

    private final Annotation[] annotations;

    public After(@NotNull AnnotatedElement element) {
        this.annotations = element.getAnnotations();
    }
}
