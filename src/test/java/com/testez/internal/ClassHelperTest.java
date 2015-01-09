package com.testez.internal;

import com.testez.annotations.Test;
import com.testez.functions.EZTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 12/31/2014
 */
public class ClassHelperTest {

    EZTest aFunctionalTest = () -> System.out.println("A Functional Test!");

    EZTest assertTest = () -> assertThat(ClassHelper.isTestElement(ClassHelper.class)).isFalse();


    @Test
    public void testGetClass() {
        System.out.println("I did it!");
        assertThat(ClassHelper.getClass("com.testez.internal.ClassHelperTest")).isEqualTo(ClassHelperTest.class);
    }
}
