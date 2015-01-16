package com.testez.internal.field;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;
import com.testez.functions.Unit;
import com.testez.internal.RunnableTest;
import com.testez.internal.report.RunnableResult;
import com.testez.internal.report.RunnableResultBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/8/2015
 */
public class EZField extends RunnableTest {

    @NotNull private final Field field;

    public EZField(@NotNull Field field) {
        super(field);
        this.field = field;
        this.field.setAccessible(true);
    }

    @Nullable
    @Override
    public RunnableResult run(Object o) {
        Class<?> fieldType = this.field.getType();
        if (fieldType == Unit.class) {
            return runUnit(this.field, o).getTestResult();
        }

        // TODO: Handle unknown test types
        return null;
    }

    @VisibleForTesting
    protected RunnableResultBuilder runUnit(@NotNull Field f, @NotNull Object o) {
        Throwable caught = null;
        Stopwatch timer = Stopwatch.createStarted();
        try {
            ((Unit) f.get(o)).test();
        } catch (Throwable e) {
            caught = e;
        }
        timer.stop();
        return new RunnableResultBuilder(f.getName(), o.getClass(), timer, caught, getExpectedExceptions());
    }
}
