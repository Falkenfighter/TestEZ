package com.testez.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.reflect.ClassPath;
import com.testez.annotations.Test;
import com.testez.functions.LambdaTest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 12/30/2014
 */
public final class ClassHelper {

    @VisibleForTesting
    protected ClassHelper() {}

    /**
     * Converts {@link java.lang.Class[]} into {@link TestEZClass[]}
     *
     * @param classes the {@link java.lang.Class[]} to convert
     * @return the {@link java.lang.Class[]} converted into {@link TestEZClass[]}
     */
    @NotNull
    public static TestEZClass[] loadTestEZClasses(@NotNull Class<?>[]... classes) {
        return Arrays.stream(classes).flatMap(Arrays::stream).filter(Objects::nonNull).map(TestEZClass::new).toArray(
                TestEZClass[]::new);
    }

    /**
     * Loads all classes in the list <code>classNames</code>
     *
     * @param classNames the classes to load
     * @return all classes found on the class path
     */
    @NotNull
    public static Class<?>[] getClasses(@NotNull List<String> classNames) {
        if (classNames.size() == 0) return new Class[0];
        return classNames.stream().map(ClassHelper::getClass).filter(Objects::nonNull).toArray(Class[]::new);
    }

    /**
     * Loads all classes in all listed packages
     *
     * @param packages the packages containing classes to load
     * @return all classes found in the containing packages
     */
    @NotNull
    public static Class<?>[] getClassesByPackages(@NotNull List<String> packages) {
        return packages.parallelStream().map(ClassHelper::getClasses).flatMap(Arrays::stream).toArray(Class[]::new);
    }


    /**
     * Loads all classes in the list <code>names</code>
     *
     * @param packageName the package to load classes from
     * @return all classes found on the class path
     */
    @NotNull
    public static Class<?>[] getClasses(@NotNull String packageName) {
        try {
            return ClassPath.from(getLoader()).getTopLevelClassesRecursive(packageName).stream()
                    .map(ClassPath.ClassInfo::load).toArray(Class[]::new);
        } catch (IOException e) {
            return new Class[0];
        }
    }

    /**
     * Loads the class <code>name</code>.
     *
     * @param name the class to load
     * @return the class or null if class is not found on the class path
     */
    @Nullable
    public static Class<?> getClass(@NotNull String name) {
        try {
            return getLoader().loadClass(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Checks if the provided {@link java.lang.reflect.AnnotatedElement} contains the
     * {@link com.testez.annotations.Test} annotation or is an instance of {@link com.testez.functions.LambdaTest}
     *
     * @param element the element to check
     * @return true if class has {@link com.testez.annotations.Test} annotation
     */
    public static boolean isTestElement(@NotNull AnnotatedElement element) {
        return element.isAnnotationPresent(Test.class) ||
                element instanceof Field &&
                        LambdaTest.class.isAssignableFrom(((Field)element).getType());
    }

    /**
     * Returns the {@link com.testez.annotations.Test} annotation on the supplied element if it exists else returns
     * null
     *
     * @param element the element to get the {@link com.testez.annotations.Test} annotation from
     * @return {@link com.testez.annotations.Test} annotation or null if the annotation does not exist
     */
    @Nullable
    public static Test getTestAnnotation(@NotNull AnnotatedElement element) {
        return (element.isAnnotationPresent(Test.class)) ? element.getAnnotation(Test.class) : null;
    }

    private static ClassLoader getLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
