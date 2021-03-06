package com.testez.internal.report;

import com.google.common.base.Stopwatch;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/9/2015
 */
public class MemberResult {
    private final String name;
    private Class clazz;
    private boolean passed;
    private Stopwatch time;
    private String cause;

    public MemberResult(String name) {
        this.name = name;
    }

    public MemberResult(String name, Class clazz, boolean passed, Stopwatch time, String cause) {
        this.name = name;
        this.clazz = clazz;
        this.passed = passed;
        this.time = time;
        this.cause = cause;
    }

    public MemberResult setClazz(Class clazz) {
        this.clazz = clazz;
        return this;
    }

    public MemberResult setPassed(boolean passed) {
        this.passed = passed;
        return this;
    }

    public MemberResult setTime(Stopwatch time) {
        this.time = time;
        return this;
    }

    public MemberResult setCause(String cause) {
        this.cause = cause;
        return this;
    }

    public String getName() {
        return name;
    }

    public Class getClazz() {
        return clazz;
    }

    public boolean isPassed() {
        return passed;
    }

    public Stopwatch getTime() {
        return time;
    }

    public String getCause() {
        return cause;
    }

    @Override
    public String toString() {
        return clazz.getCanonicalName() + "." + name + ": " + ((passed) ? "passed" : "failed") + " in " + time;
    }
}
