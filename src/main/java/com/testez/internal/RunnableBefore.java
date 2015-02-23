package com.testez.internal;

import com.testez.annotations.Before;

import java.lang.reflect.AnnotatedElement;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/14/2015
 */
public abstract class RunnableBefore extends RunnableInstance implements Configuration {
    private final Before annotation;

    public RunnableBefore(AnnotatedElement element) {
        this.annotation = element.getAnnotation(Before.class);
    }

    @Override
    public boolean isAll() {
        return annotation.value() == Invocation.ALL;
    }

    @Override
    public boolean isEach() {
        return annotation.value() == Invocation.EACH;
    }
}
