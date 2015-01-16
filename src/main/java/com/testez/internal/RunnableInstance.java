package com.testez.internal;

import com.testez.internal.report.RunnableResult;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/14/2015
 */
public interface RunnableInstance {
    public RunnableResult run(Object instance);
}
