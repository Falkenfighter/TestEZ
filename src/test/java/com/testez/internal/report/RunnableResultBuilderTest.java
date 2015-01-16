package com.testez.internal.report;

import com.google.common.base.Stopwatch;
import com.testez.functions.Unit;
import com.testez.internal.report.RunnableResult;
import com.testez.internal.report.RunnableResultBuilder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/12/2015
 */
@SuppressWarnings("unchecked")
public class RunnableResultBuilderTest {

    private final String name = "name";
    private final Class clazz = this.getClass();
    private final Stopwatch timer = Stopwatch.createUnstarted();
    private final Class[] zeroExpectedException = new Class[0];
    private final Class[] oneExpectedException = new Class[]{Exception.class};
    private Exception caughtException = null;

    Unit testConstructor = () -> new RunnableResultBuilder(name, clazz, timer, caughtException, oneExpectedException);

    Unit expectedExceptionButNotThrown = () -> {
        RunnableResult tr = new RunnableResultBuilder(name, clazz, timer, caughtException, oneExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isFalse();
        assertThat(tr.getCause()).contains("Missing expected exception[s]");
    };

    Unit expectedExceptionWasThrown = () -> {
        RunnableResult tr = new RunnableResultBuilder(name, clazz, timer, new Exception(), oneExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isTrue();
        assertThat(tr.getCause()).isNullOrEmpty();
    };

    Unit expectedExceptionButWrongOneThrown = () -> {
        RunnableResult tr = new RunnableResultBuilder(name, clazz, timer, new RuntimeException(), oneExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isFalse();
        assertThat(tr.getCause()).contains("but expected one of");
    };

    Unit unexpectedException = () -> {
        RunnableResult tr = new RunnableResultBuilder(name, clazz, timer, new RuntimeException(), zeroExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isFalse();
        assertThat(tr.getCause()).contains("Threw exception");
    };

    Unit testPassed = () -> {
        RunnableResult tr = new RunnableResultBuilder(name, clazz, timer, null, zeroExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isTrue();
    };
}
