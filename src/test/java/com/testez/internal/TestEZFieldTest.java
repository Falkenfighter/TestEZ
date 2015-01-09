package com.testez.internal;

import com.testez.functions.EZTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/9/2015
 */
public class TestEZFieldTest {
    public class FakeClass {
        EZTest failingTest = () -> { throw new Exception(); };
    }

    TestEZField field;

    EZTest testConstructor = () -> field = new TestEZField(FakeClass.class.getField("failingTest"));

    EZTest testIsTestField = () -> assertThat(field.isTestField()).isTrue();

    EZTest testRunException = () -> field.run(new FakeClass());
}
