package com.testez.internal.method;

import com.testez.internal.After;
import com.testez.internal.report.RunnableResult;

import java.lang.reflect.Method;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/14/2015
 */
public class AfterMethod extends After {
    public AfterMethod(Method method) {
        super(method);
    }

    @Override
    public RunnableResult run(Object instance) {
        return null;
    }
}
