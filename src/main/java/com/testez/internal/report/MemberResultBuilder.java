package com.testez.internal.report;

import com.beust.jcommander.internal.Nullable;
import com.google.common.base.Stopwatch;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static com.testez.internal.report.Failure.*;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/12/2015
 */
public class MemberResultBuilder {
    private final MemberResult result;

    @Nullable private final Throwable caught;
    @NotNull private final Class<? extends Throwable>[] expectedExceptions;

    public MemberResultBuilder(@NotNull String name, @NotNull Class clazz, @NotNull Stopwatch timer,
                               @Nullable Throwable caught,
                               @NotNull Class<? extends Throwable>[] expectedExceptions) {
        this.result = new MemberResult(name).setClazz(clazz).setTime(timer);
        this.caught = caught;
        this.expectedExceptions = expectedExceptions;
    }

    @NotNull
    public MemberResult getTestResult() {
        // If the test expected an exception but one was not thrown
        if (expectedExceptions.length > 0 && caught == null) {
            return result.setPassed(false).setCause(String.format(MISSING_EXCEPTION.getMessage(),
                    Arrays.asList(expectedExceptions)));
        }

        // If the test expected an exception and one was thrown
        else if (expectedExceptions.length > 0 && caught != null) {

            // If the thrown exception was expected
            if (Arrays.asList(expectedExceptions).contains(caught.getClass())) {
                return result.setPassed(true);
            }

            // If the thrown exception was unexpected
            else {
                return result.setPassed(false)
                        .setCause(String.format(UNEXPECTED_EXCEPTION.getMessage(), caught.getClass(),
                                Arrays.asList(expectedExceptions)));
            }
        }

        // If the test didn't expect an exception but one was thrown
        if (caught != null) {
            return result.setPassed(false).setCause(String.format(UNKNOWN_EXCEPTION.getMessage(), caught.getClass()));
        }

        // If the test didn't expect an exception and one was not thrown
        return result.setPassed(true);
    }
}
