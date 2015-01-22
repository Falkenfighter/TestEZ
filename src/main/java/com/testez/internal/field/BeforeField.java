package com.testez.internal.field;

import com.testez.internal.Before;
import com.testez.internal.report.MemberResult;

import java.lang.reflect.Field;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/14/2015
 */
public class BeforeField extends Before {
    public BeforeField(Field field) {
        super(field);
    }

    @Override
    public MemberResult run(Object instance) {
        return null;
    }
}
