package com.testez.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;
import com.testez.functions.EZTest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/8/2015
 */
public class TestEZField extends RunnableTest {

    @NotNull private final Field field;

    public TestEZField(@NotNull Field field) {
        super(field);
        this.field = field;
        this.field.setAccessible(true);
    }

    @Nullable
    @Override
    public TestResult run(Object o) {
        Class<?> fieldType = this.field.getType();
        if (fieldType == EZTest.class) {
            return runEZTest(this.field, o).getTestResult();
        }

        // TODO: Handle unknown test types
        return null;
    }

    @VisibleForTesting
    protected TestResultBuilder runEZTest(@NotNull Field f, @NotNull Object o) {
        Exception caughtException = null;
        Stopwatch timer = Stopwatch.createStarted();
        try {
            ((EZTest) f.get(o)).test();
        } catch (Exception e) {
            caughtException = e;
        }
        timer.stop();
        return new TestResultBuilder(f.getName(), o.getClass(), timer, caughtException, getExpectedExceptions());
    }
}
