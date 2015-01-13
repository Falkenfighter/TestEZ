package com.testez.internal;

import com.google.common.base.Stopwatch;
import com.testez.functions.EZTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/12/2015
 */
public class TestResultTest {

    private final String name = "name";
    private final Class clazz = this.getClass();
    private final boolean passed = true;
    private final Stopwatch timer = Stopwatch.createUnstarted();
    private final String cause = "cause";

    private TestResult result;

    EZTest testConstructor = () -> result = new TestResult(name);
    EZTest testConstructor2 = () -> new TestResult(name, clazz, passed, timer, cause);

    EZTest testSetClazz = () -> result.setClazz(clazz);
    EZTest testSetPassed = () -> result.setPassed(passed);
    EZTest testSetTime = () -> result.setTime(timer);
    EZTest testSetCause = () -> result.setCause(cause);

    EZTest testGetName = () ->  assertThat(result.getName()).isEqualTo(name);
    EZTest testGetClazz = () -> assertThat(result.getClazz()).isEqualTo(clazz);
    EZTest testIsPassed = () -> assertThat(result.isPassed()).isEqualTo(passed);
    EZTest testGetTime = () ->  assertThat(result.getTime()).isEqualTo(timer);
    EZTest testGetCause = () -> assertThat(result.getCause()).isEqualTo(cause);

    EZTest testToString = () -> assertThat(result.toString()).contains("name").contains("passed");

}
