package com.testez.internal.clazz;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.testez.internal.*;
import com.testez.internal.field.AfterField;
import com.testez.internal.field.BeforeField;
import com.testez.internal.field.EZField;
import com.testez.internal.method.AfterMethod;
import com.testez.internal.method.BeforeMethod;
import com.testez.internal.method.EZMethod;
import com.testez.internal.report.RunnableResult;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/7/2015
 */
public class EZClass {

    @NotNull private final Class clazz;
    @NotNull private final String name;
    @NotNull private final Field[] declaredFields;
    @NotNull private final Method[] declaredMethods;

    @NotNull private final RunnableTest[] tests;
    @NotNull private final Before[] before;
    @NotNull private final After[] after;

    public EZClass(@NotNull final Class<?> clazz) {
        this.clazz = clazz;
        this.name = clazz.getName();
        this.declaredFields = clazz.getDeclaredFields();
        this.declaredMethods = clazz.getDeclaredMethods();

        this.tests = buildTests();
        this.before = buildBefore();
        this.after = buildAfter();
    }

    public boolean isTestClass() {
        return tests.length > 0;
    }

    public RunnableResult[] run() {
        try {
            Object o = clazz.newInstance();
            return Arrays.stream(tests)
                    .map(tm -> tm.run(o))
                    .filter(Objects::nonNull)
                    .toArray(RunnableResult[]::new);
        } catch (Exception e) {
            // TODO: Handle exception
            System.out.println("EZClass ERROR:");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", this.name)
                .toString();
    }

    @NotNull
    public RunnableTest[] getTests() {
        return tests;
    }

    @VisibleForTesting
    protected RunnableTest[] buildTests() {
        return Stream.concat(
                Arrays.stream(declaredFields).parallel().filter(ClassHelper::isTestElement).map(EZField::new),
                Arrays.stream(declaredMethods).parallel().filter(ClassHelper::isTestElement).map(EZMethod::new)
        ).toArray(RunnableTest[]::new);
    }

    @VisibleForTesting
    protected Before[] buildBefore() {
        return Stream.concat(
                Arrays.stream(declaredFields).parallel().filter(ClassHelper::isBeforeElement).map(BeforeField::new),
                Arrays.stream(declaredMethods).parallel().filter(ClassHelper::isBeforeElement).map(BeforeMethod::new)
        ).toArray(Before[]::new);
    }

    @VisibleForTesting
    protected After[] buildAfter() {
        return Stream.concat(
                Arrays.stream(declaredFields).parallel().filter(ClassHelper::isAfterElement).map(AfterField::new),
                Arrays.stream(declaredMethods).parallel().filter(ClassHelper::isAfterElement).map(AfterMethod::new)
        ).toArray(After[]::new);
    }
}
