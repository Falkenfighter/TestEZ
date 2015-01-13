package com.testez.internal;

import com.testez.annotations.Test;
import com.testez.functions.EZTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/9/2015
 */
public class TestEZFieldTest {
    public class FakeClass {
        @Test(shouldThrow = {Exception.class})
        EZTest failingTest = () -> { throw new Exception(); };
    }

    TestEZField field;

    EZTest testConstructor = () -> field = new TestEZField(FakeClass.class.getDeclaredField("failingTest"));

    EZTest testIsTest = () -> assertThat(field.isTest()).isTrue();

    EZTest testRunException = () -> field.run(new FakeClass());
}
