package com.testez.internal;

import com.google.common.reflect.ClassPath;
import com.testez.functions.EZTest;
import mockit.Mock;
import mockit.MockUp;

import java.io.IOException;
import java.util.Arrays;

import static com.testez.internal.ClassHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 12/31/2014
 */
public class ClassHelperTest {

    private final String internalPackagePath = "com.testez.internal";
    private final String classHelperTestPath = internalPackagePath + ".ClassHelperTest";

    EZTest testConstructor = ClassHelper::new;

    EZTest testLoadTestEZClasses = () ->
            assertThat(loadTestEZClasses(new Class[]{ClassHelperTest.class})).hasSize(1);

    EZTest testGetClassesByClassName = () ->
            assertThat(getClasses(Arrays.asList(classHelperTestPath))).hasSize(1);

    EZTest testGetClassesByPackages = () ->
            assertThat(getClassesByPackages(Arrays.asList(internalPackagePath))).isNotEmpty();

    EZTest testGetClassesByPackage = () ->
            assertThat(getClasses(internalPackagePath)).isNotEmpty();

    EZTest testGetClassesByPackageUnknownPackage = () ->
            assertThat(getClasses(internalPackagePath + ".unknown")).isEmpty();

    EZTest testGetClassesByPackageIOException = () -> {
        new MockUp<ClassPath>() {
            @Mock
            public ClassPath from(ClassLoader loader) throws IOException {
                throw new IOException();
            }
        };
        assertThat(getClasses(internalPackagePath)).isEmpty();
    };

    EZTest testGetClass = () ->
            assertThat(ClassHelper.getClass(classHelperTestPath)).isEqualTo(ClassHelperTest.class);

    EZTest testGetClassNotFound = () ->
            assertThat(ClassHelper.getClass(internalPackagePath + ".UnknownClass")).isNull();

    EZTest testIsTestElement = () ->
            assertThat(isTestElement(ClassHelper.class)).isFalse();
}
