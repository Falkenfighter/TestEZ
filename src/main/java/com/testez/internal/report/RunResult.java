package com.testez.internal.report;

import com.google.common.base.Strings;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/8/2015
 */
public class RunResult {

    private final MemberResult[] allTests;
    private final MemberResult[] failedTests;
    private final MemberResult[] passedTests;

    public RunResult(MemberResult[] results) {
        this.allTests = results;
        this.passedTests = Arrays.stream(results).filter(MemberResult::isPassed).toArray(MemberResult[]::new);
        this.failedTests = Arrays.stream(results).filter(tr -> !tr.isPassed()).toArray(MemberResult[]::new);
    }

    public MemberResult[] getFailedTests() {
        return failedTests;
    }

    public MemberResult[] getPassedTests() {
        return passedTests;
    }

    @Override
    public String toString() {
        String output = "Ran: " + allTests.length + ", Passed: " + passedTests.length + ", Failed: " + failedTests.length;
        String header = "========================== TestEZ ==========================";
        String result = Strings.padStart(output, output.length() + ((header.length() - output.length())/2), ' ');
        String footer = "============================================================";
        String fTests = Arrays.stream(failedTests).map(MemberResult::toString).collect(Collectors.joining("\n"));
        return "\n" + header + "\n" + result + "\n" + footer + "\n" + fTests;
    }
}
