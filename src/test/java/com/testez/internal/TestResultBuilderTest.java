package com.testez.internal;

import com.google.common.base.Stopwatch;
import com.testez.functions.EZTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/12/2015
 */
@SuppressWarnings("unchecked")
public class TestResultBuilderTest {

    private final String name = "name";
    private final Class clazz = this.getClass();
    private final Stopwatch timer = Stopwatch.createUnstarted();
    private final Class[] zeroExpectedException = new Class[0];
    private final Class[] oneExpectedException = new Class[]{Exception.class};
    private Exception caughtException = null;

    EZTest testConstructor = () -> new TestResultBuilder(name, clazz, timer, caughtException, oneExpectedException);

    EZTest expectedExceptionButNotThrown = () -> {
        TestResult tr = new TestResultBuilder(name, clazz, timer, caughtException, oneExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isFalse();
        assertThat(tr.getCause()).contains("Missing expected exception[s]");
    };

    EZTest expectedExceptionWasThrown = () -> {
        TestResult tr = new TestResultBuilder(name, clazz, timer, new Exception(), oneExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isTrue();
        assertThat(tr.getCause()).isNullOrEmpty();
    };

    EZTest expectedExceptionButWrongOneThrown = () -> {
        TestResult tr = new TestResultBuilder(name, clazz, timer, new RuntimeException(), oneExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isFalse();
        assertThat(tr.getCause()).contains("but expected one of");
    };

    EZTest unexpectedException = () -> {
        TestResult tr = new TestResultBuilder(name, clazz, timer, new RuntimeException(), zeroExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isFalse();
        assertThat(tr.getCause()).contains("Threw exception");
    };

    EZTest testPassed = () -> {
        TestResult tr = new TestResultBuilder(name, clazz, timer, null, zeroExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isTrue();
    };
}
