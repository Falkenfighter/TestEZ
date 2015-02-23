package com.testez.annotations;

import static com.testez.internal.Invocation.ALL;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 2/9/2015
 */
public class BeforeAllTest {
    private int i = 0;

    @Before(ALL)
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
