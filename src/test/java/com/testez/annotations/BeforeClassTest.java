package com.testez.annotations;

import static com.testez.internal.Invocation.CLASS;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 2/9/2015
 */
public class BeforeClassTest {
    private int i = 0;

    @Before(CLASS)
    public void setup() {
        i++;
    }

    @Test
    public void firstTest() {
        assertThat(i).isEqualTo(1);
    }

    @Test
    public void secondTest() {
        assertThat(i).isEqualTo(1);
    }
}
