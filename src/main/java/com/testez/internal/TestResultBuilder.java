package com.testez.internal;

import com.beust.jcommander.internal.Nullable;
import com.google.common.base.Stopwatch;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static com.testez.internal.Failure.*;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/12/2015
 */
public class TestResultBuilder {
    private final TestResult result;

    @Nullable private final Exception caughtException;
    @NotNull private final Class<? extends Throwable>[] expectedExceptions;

    public TestResultBuilder(@NotNull String name, @NotNull Class clazz, @NotNull Stopwatch timer,
                             @Nullable Exception caughtException,
                             @NotNull Class<? extends Throwable>[] expectedExceptions) {
        this.result = new TestResult(name).setClazz(clazz).setTime(timer);
        this.caughtException = caughtException;
        this.expectedExceptions = expectedExceptions;
    }

    @NotNull
    public TestResult getTestResult() {
        // If the test expected an exception but one was not thrown
        if (expectedExceptions.length > 0 && caughtException == null) {
            return result.setPassed(false).setCause(String.format(MISSING_EXCEPTION.getMessage(),
                    Arrays.asList(expectedExceptions)));
        }

        // If the test expected an exception and one was thrown
        else if (expectedExceptions.length > 0 && caughtException != null) {

            // If the thrown exception was expected
            if (Arrays.asList(expectedExceptions).contains(caughtException.getClass())) {
                return result.setPassed(true);
            }

            // If the thrown exception was unexpected
            else {
                return result.setPassed(false)
                        .setCause(String.format(UNEXPECTED_EXCEPTION.getMessage(), caughtException.getClass(),
                                Arrays.asList(expectedExceptions)));
            }
        }

        // If the test didn't expect an exception but one was thrown
        if (caughtException != null) {
            return result.setPassed(false).setCause(String.format(UNKNOWN_EXCEPTION.getMessage(), caughtException.getClass()));
        }

        // If the test didn't expect an exception and one was not thrown
        return result.setPassed(true);
    }
}
