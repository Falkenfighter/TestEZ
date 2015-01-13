package com.testez.internal;

import com.google.common.base.Stopwatch;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/7/2015
 */
public class TestEZMethod extends RunnableTest {
    @NotNull private final Method method;
    @NotNull private final Class<?> clazz;
    @NotNull private final String name;
    @NotNull private final Class<?> returnType;
    @NotNull private final Class<?>[] parameters;
    @NotNull private final Annotation[] annotations;

    public TestEZMethod(@NotNull final Method method) {
        super(method);
        this.method = method;
        this.clazz = method.getDeclaringClass();
        this.name = method.getName();
        this.returnType = method.getReturnType();
        this.parameters = method.getParameterTypes();
        this.annotations = method.getDeclaredAnnotations();
    }

    public boolean isPublic() {
        return Modifier.isPublic(method.getModifiers());
    }

    @Override
    public TestResult run(Object o) {
        Stopwatch timer = Stopwatch.createStarted();
        Exception caughtException = null;

        try {
            method.invoke(o);
        } catch (Exception e) {
            caughtException = e;
        }

        return new TestResultBuilder(
                method.getName(), o.getClass(), timer.stop(), caughtException, getExpectedExceptions()
        ).getTestResult();
    }
}
