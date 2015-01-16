package com.testez.internal.report;

import com.google.common.base.Strings;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/8/2015
 */
public class RunResult {

    private final RunnableResult[] allTests;
    private final RunnableResult[] failedTests;
    private final RunnableResult[] passedTests;

    public RunResult(RunnableResult[] results) {
        this.allTests = results;
        this.passedTests = Arrays.stream(results).filter(RunnableResult::isPassed).toArray(RunnableResult[]::new);
        this.failedTests = Arrays.stream(results).filter(tr -> !tr.isPassed()).toArray(RunnableResult[]::new);
    }

    public RunnableResult[] getFailedTests() {
        return failedTests;
    }

    public RunnableResult[] getPassedTests() {
        return passedTests;
    }

    @Override
    public String toString() {
        String output = "Ran: " + allTests.length + ", Passed: " + passedTests.length + ", Failed: " + failedTests.length;
        String header = "========================== TestEZ ==========================";
        String result = Strings.padStart(output, output.length() + ((header.length() - output.length())/2), ' ');
        String footer = "============================================================";
        String fTests = Arrays.stream(failedTests).map(RunnableResult::toString).collect(Collectors.joining("\n"));
        return "\n" + header + "\n" + result + "\n" + footer + "\n" + fTests;
    }
}
