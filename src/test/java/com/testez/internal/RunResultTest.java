package com.testez.internal;

import com.testez.functions.EZTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/12/2015
 */
public class RunResultTest {
    private RunResult result;

    EZTest testConstructor = () -> result = new RunResult(new TestResult[0]);

    EZTest testGetFailedTests = () -> assertThat(result.getFailedTests()).isEmpty();

    EZTest testGetPassedTests = () -> assertThat(result.getPassedTests()).isEmpty();

    EZTest testToString = () -> assertThat(result.toString())
            .contains("TestEZ")
            .contains("Ran:")
            .contains("Passed:")
            .contains("Failed:");
}
