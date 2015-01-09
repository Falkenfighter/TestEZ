package com.testez.internal;

import com.testez.functions.EZTest;

import java.lang.reflect.Field;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/8/2015
 */
public class TestEZField implements RunnableTest {
    private final Field field;
    private final boolean isTestField;

    public TestEZField(Field field) {
        this.field = field;
        this.isTestField = ClassHelper.isTestElement(field);
    }

    public boolean isTestField() {
        return isTestField;
    }

    @Override
    public void run(Object o) {
        Class<?> fieldType = field.getType().getClass();
        field.setAccessible(true);
        if (fieldType.isInstance(EZTest.class)) {
            try {
                ((EZTest) field.get(o)).test();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
