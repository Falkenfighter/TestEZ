package com.testez.internal;

import com.testez.functions.EZTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/9/2015
 */
public class TestEZMethodTest {
    public void mockMethod() { /* Does Nothing! */ }

    TestEZMethod method;

    EZTest testConstructor = () -> method = new TestEZMethod(TestEZMethodTest.class.getMethod("mockMethod"));

    EZTest testIsPublic = () -> assertThat(method.isPublic()).isTrue();

    EZTest testIsTest = () -> assertThat(method.isTest()).isFalse();

    EZTest testRun = () -> method.run(new TestEZMethodTest());

    EZTest testRunError = () -> method.run(null);

}
