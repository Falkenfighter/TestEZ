package com.testez.functions;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/8/2015
 */
@FunctionalInterface
public interface EZParametrizedTest extends LambdaTest {
    public void test(Object... o);
}
