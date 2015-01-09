package com.testez.internal;

import com.testez.functions.EZTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/9/2015
 */
public class TestEZClassTest {
    public class EmptyClass {
    }

    TestEZClass testClass;

    EZTest testConstructor = () -> testClass = new TestEZClass(EmptyClass.class);

    EZTest testRunUnknownException = () -> assertThat(testClass.toString()).contains("EmptyClass");

    EZTest testBuildTestEZMethods = () -> assertThat(testClass.buildTestEZMethods(EmptyClass.class)).isEmpty();

}
