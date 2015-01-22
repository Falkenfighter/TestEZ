package com.testez.internal.method;

import com.testez.internal.Before;
import com.testez.internal.report.MemberResult;

import java.lang.reflect.Method;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/14/2015
 */
public class BeforeMethod extends Before {
    public BeforeMethod(Method method) {
        super(method);
    }

    @Override
    public MemberResult run(Object instance) {
        return null;
    }
}