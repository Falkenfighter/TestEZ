package com.testez.internal.report;

import com.google.common.base.Stopwatch;
import com.testez.functions.Unit;
import com.testez.internal.report.RunnableResult;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/12/2015
 */
public class RunnableResultTest {

    private final String name = "name";
    private final Class clazz = this.getClass();
    private final boolean passed = true;
    private final Stopwatch timer = Stopwatch.createUnstarted();
    private final String cause = "cause";

    private RunnableResult result;

    Unit testConstructor = () -> result = new RunnableResult(name);
    Unit testConstructor2 = () -> new RunnableResult(name, clazz, passed, timer, cause);

    Unit testSetClazz = () -> result.setClazz(clazz);
    Unit testSetPassed = () -> result.setPassed(passed);
    Unit testSetTime = () -> result.setTime(timer);
    Unit testSetCause = () -> result.setCause(cause);

    Unit testGetName = () ->  assertThat(result.getName()).isEqualTo(name);
    Unit testGetClazz = () -> assertThat(result.getClazz()).isEqualTo(clazz);
    Unit testIsPassed = () -> assertThat(result.isPassed()).isEqualTo(passed);
    Unit testGetTime = () ->  assertThat(result.getTime()).isEqualTo(timer);
    Unit testGetCause = () -> assertThat(result.getCause()).isEqualTo(cause);

    Unit testToString = () -> assertThat(result.toString()).contains("name").contains("passed");

}
