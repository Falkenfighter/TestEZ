package com.testez.annotations;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 2/9/2015
 */
public class BeforeSpecificTest {
    private int i = 0;

    @Before(tests = {"secondTest", "fourthTest"})
    public void setup() {
        i++;
    }

    @Test
    public void firstTest() {
        assertThat(i).isEqualTo(0);
    }

    @Test
    public void secondTest() {
        assertThat(i).isEqualTo(1);
    }

    @Test
    public void thirdTest() {
        assertThat(i).isEqualTo(1);
    }

    @Test
    public void fourthTest() {
        assertThat(i).isEqualTo(2);
    }
}