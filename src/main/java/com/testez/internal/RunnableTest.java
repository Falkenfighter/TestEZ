package com.testez.internal;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.AnnotatedElement;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/8/2015
 */
public abstract class RunnableTest {
    private final boolean isTest;

    public RunnableTest(@NotNull AnnotatedElement element) {
        isTest = ClassHelper.isTestElement(element);
    }

    public abstract TestResult run(Object o);

    public boolean isTest() {
        return isTest;
    }
}
