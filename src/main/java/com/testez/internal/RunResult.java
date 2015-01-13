package com.testez.internal;

import com.google.common.base.Strings;

import java.util.Arrays;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/8/2015
 */
public class RunResult {

    private final TestResult[] allTests;
    private final TestResult[] failedTests;
    private final TestResult[] passedTests;

    public RunResult(TestResult[] results) {
        this.allTests = results;
        this.passedTests = Arrays.stream(results).filter(TestResult::isPassed).toArray(TestResult[]::new);
        this.failedTests = Arrays.stream(results).filter(tr -> !tr.isPassed()).toArray(TestResult[]::new);
    }

    public TestResult[] getFailedTests() {
        return failedTests;
    }

    public TestResult[] getPassedTests() {
        return passedTests;
    }

    @Override
    public String toString() {
        String output = "Ran: " + allTests.length + ", Passed: " + passedTests.length + ", Failed: " + failedTests.length;
        String header = "========================== TestEZ ==========================";
        String result = Strings.padStart(output, output.length() + ((header.length() - output.length())/2), ' ');
        String footer = "============================================================";
        return header + "\n" + result + "\n" + footer;
    }
}
