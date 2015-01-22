package com.testez.internal.report;

import com.google.common.base.Stopwatch;
import com.testez.functions.Unit;

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

    Unit testConstructor = () -> new MemberResultBuilder(name, clazz, timer, caughtException, oneExpectedException);

    Unit expectedExceptionButNotThrown = () -> {
        MemberResult tr = new MemberResultBuilder(name, clazz, timer, caughtException, oneExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isFalse();
        assertThat(tr.getCause()).contains("Missing expected exception[s]");
    };

    Unit expectedExceptionWasThrown = () -> {
        MemberResult tr = new MemberResultBuilder(name, clazz, timer, new Exception(), oneExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isTrue();
        assertThat(tr.getCause()).isNullOrEmpty();
    };

    Unit expectedExceptionButWrongOneThrown = () -> {
        MemberResult tr = new MemberResultBuilder(name, clazz, timer, new RuntimeException(), oneExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isFalse();
        assertThat(tr.getCause()).contains("but expected one of");
    };

    Unit unexpectedException = () -> {
        MemberResult tr = new MemberResultBuilder(name, clazz, timer, new RuntimeException(), zeroExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isFalse();
        assertThat(tr.getCause()).contains("Threw exception");
    };

    Unit testPassed = () -> {
        MemberResult tr = new MemberResultBuilder(name, clazz, timer, null, zeroExpectedException)
                .getTestResult();
        assertThat(tr.isPassed()).isTrue();
    };
}
