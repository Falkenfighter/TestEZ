package com.testez.internal.clazz;

import com.testez.functions.Unit;
import mockit.Mock;
import mockit.MockUp;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 1/9/2015
 */
public class EZClassTest {
    private class EmptyClass {}

    private EZClass emptyClass;
    private EZClass testClass;

    Unit testConstructor = () -> {
        emptyClass = new EZClass(EmptyClass.class);
        testClass = new EZClass(EZClassTest.class);
    };
    Unit testIsTestClassFalse = () -> assertThat(emptyClass.isTestClass()).isFalse();
    Unit testIsTestClassTrue = () -> assertThat(testClass.isTestClass()).isTrue();
    Unit testGetTests = () -> assertThat(testClass.getTests()).isNotEmpty();
    Unit testGetTestsEmpty = () -> assertThat(emptyClass.getTests()).isEmpty();
    Unit testToString = () -> assertThat(emptyClass.toString()).contains("name").contains("EmptyClass");
//    Unit testRunWithException = () -> { // TODO: Need to have this only run in this unit tests context!
//        new MockUp<Class<EmptyClass>>() {
//            @Mock
//            public EmptyClass newInstance() throws InstantiationException, IllegalAccessException {
//                throw new InstantiationException();
//            }
//        };
//        emptyClass.run();
//    };
}
