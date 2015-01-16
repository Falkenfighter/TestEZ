package com.testez.internal.clazz;

import com.google.common.reflect.ClassPath;
import com.testez.functions.Unit;
import mockit.Mock;
import mockit.MockUp;

import java.io.IOException;
import java.util.Arrays;

import static com.testez.internal.clazz.ClassHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 12/31/2014
 */
public class ClassHelperTest {

    private final String internalPackagePath = "com.testez.internal.clazz";
    private final String classHelperTestPath = internalPackagePath + ".ClassHelperTest";

    Unit testConstructor = ClassHelper::new;

    Unit testLoadTestEZClasses = () ->
            assertThat(loadTestEZClasses(new Class[]{ClassHelperTest.class})).hasSize(1);

    Unit testGetClassesByClassName = () ->
            assertThat(getClasses(Arrays.asList(classHelperTestPath))).hasSize(1);

    Unit testGetClassesByPackages = () ->
            assertThat(getClassesByPackages(Arrays.asList(internalPackagePath))).isNotEmpty();

    Unit testGetClassesByPackage = () ->
            assertThat(getClasses(internalPackagePath)).isNotEmpty();

    Unit testGetClassesByPackageUnknownPackage = () ->
            assertThat(getClasses(internalPackagePath + ".unknown")).isEmpty();

    Unit testGetClassesByPackageIOException = () -> {
        new MockUp<ClassPath>() {
            @Mock
            public ClassPath from(ClassLoader loader) throws IOException {
                throw new IOException();
            }
        };
        assertThat(getClasses(internalPackagePath)).isEmpty();
    };

    Unit testGetClass = () ->
            assertThat(ClassHelper.getClass(classHelperTestPath)).isEqualTo(ClassHelperTest.class);

    Unit testGetClassNotFound = () ->
            assertThat(ClassHelper.getClass(internalPackagePath + ".UnknownClass")).isNull();

    Unit testIsTestElement = () ->
            assertThat(isTestElement(ClassHelper.class)).isFalse();
}
