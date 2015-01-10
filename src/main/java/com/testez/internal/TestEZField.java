package com.testez.internal;

import com.google.common.base.Stopwatch;
import com.testez.functions.EZTest;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalTime;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/8/2015
 */
public class TestEZField extends RunnableTest {
    private static final Logger logger = LoggerFactory.getLogger(TestEZField.class);
    private final Field field;

    public TestEZField(Field field) {
        super(field);
        this.field = field;
    }

    @Nullable
    @Override
    public TestResult run(Object o) {
        Class<?> fieldType = field.getType();
        if (fieldType == EZTest.class) {
            field.setAccessible(true);
            boolean passed = false;
            Stopwatch timer = Stopwatch.createStarted();
            try {
                ((EZTest) field.get(o)).test();
                passed = true;
            } catch (Exception e) {
                logger.warn("Test [{}] threw exception", field.getName(), e);
            }
            return new TestResult(o.getClass().getSimpleName() + "." + field.getName(), passed, timer.stop());
        }
        return null;
    }
}
