package com.testez.internal;

import com.google.common.annotations.VisibleForTesting;
import com.testez.annotations.Test;
import com.testez.internal.clazz.ClassHelper;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.AnnotatedElement;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/8/2015
 */
public abstract class RunnableTest extends RunnableInstance {
    private final boolean isTest;
    private final Test testAnnotation;

    public RunnableTest(@NotNull AnnotatedElement element) {
        isTest = ClassHelper.isTestElement(element);
        testAnnotation = ClassHelper.getTestAnnotation(element);
    }

    @NotNull
    @VisibleForTesting
    protected Class<? extends Throwable>[] getExpectedExceptions() {
        return (testAnnotation == null) ? new Class[0] : testAnnotation.shouldThrow();
    }

    public boolean isTest() {
        return isTest;
    }
}
