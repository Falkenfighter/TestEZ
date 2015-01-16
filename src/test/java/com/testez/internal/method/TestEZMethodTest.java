package com.testez.internal.method;

import com.testez.annotations.Test;
import com.testez.functions.Unit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/9/2015
 */
public class TestEZMethodTest {
    public void mockMethod() { /* Does Nothing! */ }

    EZMethod method;

//    @BeforeAll
//    Helper initTestEZMethod = () -> method = new EZMethod(TestEZMethodTest.class.getMethod("mockMethod"));

    Unit testConstructor = () -> method = new EZMethod(TestEZMethodTest.class.getMethod("mockMethod"));

    Unit testIsPublic = () -> assertThat(method.isPublic()).isTrue();

    Unit testIsTest = () -> assertThat(method.isTest()).isFalse();

    Unit testRun = () -> method.run(new TestEZMethodTest());

    @Test(shouldThrow = NullPointerException.class)
    Unit testRunError = () -> method.run(null);
}
