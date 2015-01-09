package com.testez.internal;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/7/2015
 */
public class TestEZMethod implements RunnableTest {
    @NotNull private final Method method;
    @NotNull private final Class<?> clazz;
    @NotNull private final String name;
    @NotNull private final Class<?> returnType;
    @NotNull private final Class<?>[] parameters;
    @NotNull private final Annotation[] annotations;
    private final boolean hasTestAnnotation;

    public TestEZMethod(final Method method) {
        this.method = method;
        this.clazz = method.getDeclaringClass();
        this.name = method.getName();
        this.returnType = method.getReturnType();
        this.parameters = method.getParameterTypes();
        this.annotations = method.getDeclaredAnnotations();
        this.hasTestAnnotation = ClassHelper.isTestElement(method);
    }

    public boolean isPublic() {
        return Modifier.isPublic(method.getModifiers());
    }

    public boolean hasTestAnnotation() {
        return this.hasTestAnnotation;
    }

    @Override
    public void run(Object o) {
        try {
            method.invoke(o);
        } catch (Exception e) {
            // TODO: Handle exception
        }
    }
}
