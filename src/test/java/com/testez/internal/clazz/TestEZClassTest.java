package com.testez.internal.clazz;

import com.testez.functions.Unit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/9/2015
 */
public class TestEZClassTest {
    public class EmptyClass {}
    Unit testConstructor = () -> new EZClass(EmptyClass.class);
    Unit testIsTestClassFalse = () -> assertThat(new EZClass(EmptyClass.class).isTestClass()).isFalse();
    Unit testIsTestClassTrue = () -> assertThat(new EZClass(TestEZClassTest.class).isTestClass()).isTrue();
    Unit testGetTests = () -> assertThat(new EZClass(TestEZClassTest.class).getTests()).isNotEmpty();
    Unit testGetTestsEmpty = () -> assertThat(new EZClass(EmptyClass.class).getTests()).isEmpty();
}
