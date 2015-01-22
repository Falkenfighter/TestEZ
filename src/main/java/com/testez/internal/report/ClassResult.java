package com.testez.internal.report;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/16/2015
 */
public class ClassResult {
    @NotNull private final Class<?> clazz;
    @NotNull private final MemberResult[] memberResults;
    private Exception exception;
    private boolean passed = false;

    public ClassResult(@NotNull Class<?> clazz, @NotNull MemberResult[] memberResults) {
        this.clazz = clazz;
        this.memberResults = memberResults;
        passed = Arrays.stream(memberResults)
                .filter(mr -> !mr.isPassed())
                .toArray(MemberResult[]::new).length == 0;
    }

    public ClassResult(@NotNull Class<?> clazz, @NotNull Exception e) {
        this.clazz = clazz;
        this.memberResults = new MemberResult[0];
        this.exception = e;
    }

    @NotNull
    public Class<?> getClazz() {
        return clazz;
    }

    @NotNull
    public MemberResult[] getMemberResults() {
        return memberResults;
    }

    public boolean isPassed() {
        return passed;
    }

    public Exception getException() {
        return exception;
    }
}
