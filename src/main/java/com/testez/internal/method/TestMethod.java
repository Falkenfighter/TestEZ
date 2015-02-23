package com.testez.internal.method;

import com.google.common.base.Stopwatch;
import com.testez.internal.RunnableTest;
import com.testez.internal.report.MemberResult;
import com.testez.internal.report.MemberResultBuilder;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/7/2015
 */
public class TestMethod extends RunnableTest {
    @NotNull private final Method method;
    @NotNull private final Class<?> clazz;
    @NotNull private final String name;
    @NotNull private final Class<?> returnType;
    @NotNull private final Class<?>[] parameters;
    @NotNull private final Annotation[] annotations;

    public TestMethod(@NotNull final Method method) {
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
    public MemberResult run(Object o) {
        Stopwatch timer = Stopwatch.createStarted();
        Exception caughtException = null;

        try {
            method.invoke(o);
        } catch (Exception e) {
            caughtException = e;
        }

        return new MemberResultBuilder(
                method.getName(), o.getClass(), timer.stop(), caughtException, getExpectedExceptions()
        ).getTestResult();
    }
}
