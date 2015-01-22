package com.testez.internal.report;

import com.testez.functions.Unit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/12/2015
 */
public class RunResultTest {
    private RunResult result;

    Unit testConstructor = () -> result = new RunResult(new MemberResult[0]);

    Unit testGetFailedTests = () -> assertThat(result.getFailedTests()).isEmpty();

    Unit testGetPassedTests = () -> assertThat(result.getPassedTests()).isEmpty();

    Unit testToString = () -> assertThat(result.toString())
            .contains("TestEZ")
            .contains("Ran:")
            .contains("Passed:")
            .contains("Failed:");
}
