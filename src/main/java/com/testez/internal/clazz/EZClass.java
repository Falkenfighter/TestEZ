package com.testez.internal.clazz;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.testez.internal.*;
import com.testez.internal.field.AfterField;
import com.testez.internal.field.BeforeField;
import com.testez.internal.field.TestField;
import com.testez.internal.method.AfterMethod;
import com.testez.internal.method.BeforeMethod;
import com.testez.internal.method.TestMethod;
import com.testez.internal.report.ClassResult;
import com.testez.internal.report.MemberResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
    @NotNull private final RunnableBefore[] before;
    @NotNull private final RunnableAfter[] after;

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

    @NotNull
    public ClassResult run() {
        Object o = getInstance();
        return new ClassResult(clazz, getRunOrder().stream()
                .map(tm -> tm.run(o))
                .filter(Objects::nonNull)
                .toArray(MemberResult[]::new));
    }

    private Queue<RunnableInstance> getRunOrder() {
        Queue<RunnableInstance> queue = new LinkedList<>();

        // Add all before all methods as first to run
        queue.addAll(Arrays.stream(before).filter(RunnableBefore::isAll).collect(toList()));

        Arrays.stream(tests).forEach(t -> {
            // Add all before each methods before every test
            queue.addAll(Arrays.stream(before).filter(RunnableBefore::isEach).collect(toList()));
            // Add test
            queue.add(t);
            // Add all after each methods after every test
            queue.addAll(Arrays.stream(after).filter(RunnableAfter::isEach).collect(toList()));
        });

        // Add all after all methods as last to run
        queue.addAll(Arrays.stream(after).filter(RunnableAfter::isAll).collect(toList()));

        return queue;
    }

    @Nullable
    public Object getInstance() {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace(); // TODO: handle this in a cleaner way
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

    @NotNull
    public RunnableBefore[] getBefore() {
        return before;
    }

    @NotNull
    public RunnableAfter[] getAfter() {
        return after;
    }

    @VisibleForTesting
    protected RunnableTest[] buildTests() {
        return Stream.concat(
                Arrays.stream(declaredFields).parallel().filter(ClassHelper::isTestElement).map(TestField::new),
                Arrays.stream(declaredMethods).parallel().filter(ClassHelper::isTestElement).map(TestMethod::new)
        ).toArray(RunnableTest[]::new);
    }

    @VisibleForTesting
    protected RunnableBefore[] buildBefore() {
        return Stream.concat(
                Arrays.stream(declaredFields).parallel().filter(ClassHelper::isBeforeElement).map(BeforeField::new),
                Arrays.stream(declaredMethods).parallel().filter(ClassHelper::isBeforeElement).map(BeforeMethod::new)
        ).toArray(RunnableBefore[]::new);
    }

    @VisibleForTesting
    protected RunnableAfter[] buildAfter() {
        return Stream.concat(
                Arrays.stream(declaredFields).parallel().filter(ClassHelper::isAfterElement).map(AfterField::new),
                Arrays.stream(declaredMethods).parallel().filter(ClassHelper::isAfterElement).map(AfterMethod::new)
        ).toArray(RunnableAfter[]::new);
    }
}
