package com.testez.internal;

import com.testez.annotations.After;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.AnnotatedElement;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/14/2015
 */
public abstract class RunnableAfter extends RunnableInstance implements Configuration {

    private final After annotation;

    public RunnableAfter(@NotNull AnnotatedElement element) {
        this.annotation = element.getAnnotation(After.class);
    }

    @Override
    public boolean isClass() {
        return annotation.value() == Invocation.CLASS;
    }

    @Override
    public boolean isEach() {
        return annotation.value() == Invocation.EACH;
    }
}
