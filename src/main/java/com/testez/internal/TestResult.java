package com.testez.internal;

import com.google.common.base.Stopwatch;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/9/2015
 */
public class TestResult {
    String name;
    boolean passed;
    Stopwatch time;

    public TestResult(String name, boolean passed, Stopwatch time) {
        this.name = name;
        this.passed = passed;
        this.time = time;
    }

    @Override
    public String toString() {
        return name + ": " + ((passed) ? "passed" : "failed") + " in " + time;
    }
}
