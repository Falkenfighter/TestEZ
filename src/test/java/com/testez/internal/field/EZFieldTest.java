package com.testez.internal.field;

import com.testez.annotations.Test;
import com.testez.functions.Unit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/9/2015
 */
public class EZFieldTest {
    public class FakeClass {
        @Test(shouldThrow = {Exception.class})
        Unit failingTest = () -> { throw new Exception(); };
    }

    EZField field;

    Unit testConstructor = () -> field = new EZField(FakeClass.class.getDeclaredField("failingTest"));

    Unit testIsTest = () -> assertThat(field.isTest()).isTrue();

    Unit testRunException = () -> field.run(new FakeClass());
}
