package com.testez.annotations;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 12/30/2014
 */
public class AnnotationsTest {

    private final String testString = "value";

    @Test(saveValue = true)
    public String firstTest() {
        assertThat(true).isTrue();
        return testString;
    }

    @Inject
    @Test(dependsOn = "firstTest")
    public void secondTest(String value) {
        assertThat(true).isFalse();
        assertThat(value).isEqualTo(testString);
    }

    @Test(dependsOn = {"AnnotationsTest.firstTest", ""})
    public void thirdTest() {

    }
}
