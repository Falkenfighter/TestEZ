package com.testez.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/7/2015
 */
public class TestEZClass {

    @NotNull private final Class clazz;
    @NotNull private final String name;
    @NotNull private final Annotation[] annotations;
    @NotNull private final Constructor<?>[] constructors;
    @NotNull private final TestEZField[] allFields;
    @NotNull private final TestEZField[] testFields;
    @NotNull private final TestEZMethod[] allMethods;
    @NotNull private final TestEZMethod[] testMethods;
    private final boolean testClass;

    public TestEZClass(@NotNull final Class<?> clazz) {
        this.clazz = clazz;
        this.name = clazz.getName();
        this.annotations = clazz.getDeclaredAnnotations();
        this.constructors = clazz.getDeclaredConstructors();
        this.allFields = this.buildTestEZFields(clazz);
        this.allMethods = this.buildTestEZMethods(clazz);
        this.testFields = this.findActualTestFields(this.allFields);

        boolean classHasTestAnnotation = ClassHelper.isTestElement(clazz);
        this.testMethods = this.findActualTestMethods(this.allMethods, classHasTestAnnotation);
        this.testClass = testMethods.length > 0 || classHasTestAnnotation;
    }

    public boolean isTestClass() {
        return testClass;
    }

    public void run() {
        try {
            Object o = clazz.newInstance();
            Stream.concat(Arrays.stream(testFields), Arrays.stream(testMethods))
                    .forEach((RunnableTest rt) -> rt.run(o));
            Arrays.stream(testMethods).forEach(tm -> tm.run(o));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", this.name)
                .toString();
    }

    /**
     * Converts all declared methods into {@link com.testez.internal.TestEZMethod}'s
     *
     * @param clazz the class to build {@link com.testez.internal.TestEZMethod}'s from
     * @return the array of {@link com.testez.internal.TestEZMethod}'s
     */
    @NotNull
    @VisibleForTesting
    @Contract("null -> fail")
    protected TestEZMethod[] buildTestEZMethods(@NotNull Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods()).map(TestEZMethod::new).toArray(TestEZMethod[]::new);
    }

    /**
     * Filters the <code>allClassMethods</code> argument by check if the <code>classHasTestAnnotation</code> is true
     * and the method has public scope or if the method is annotated with {@link com.testez.annotations.Test}
     *
     * @param allClassMethods        array of all methods in the current test class
     * @param classHasTestAnnotation whether the test class is annotated with {@link com.testez.annotations.Test}
     * @return {@link com.testez.internal.TestEZMethod}'s which are valid tests
     */
    @NotNull
    @VisibleForTesting
    @Contract("null, _ -> fail")
    protected TestEZMethod[] findActualTestMethods(@NotNull TestEZMethod[] allClassMethods,
                                                   boolean classHasTestAnnotation) {
        return Arrays.stream(allClassMethods)
                .filter(method -> (classHasTestAnnotation && method.isPublic()) || method.hasTestAnnotation())
                .toArray(TestEZMethod[]::new);
    }

    /**
     * Converts all declared fields into {@link com.testez.internal.TestEZField}'s
     *
     * @param clazz the class to build {@link com.testez.internal.TestEZField}'s from
     * @return the array of {@link com.testez.internal.TestEZField}'s
     */
    @NotNull
    @VisibleForTesting
    @Contract("null -> fail")
    protected TestEZField[] buildTestEZFields(@NotNull Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields()).map(TestEZField::new).toArray(TestEZField[]::new);
    }

    @NotNull
    @VisibleForTesting
    @Contract("null -> fail")
    protected TestEZField[] findActualTestFields(TestEZField[] allFields) {
        return Arrays.stream(allFields).filter(TestEZField::isTestField).toArray(TestEZField[]::new);
    }
}
