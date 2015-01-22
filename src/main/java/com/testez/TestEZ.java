package com.testez;

import com.beust.jcommander.JCommander;
import com.testez.internal.clazz.EZClass;
import com.testez.internal.exception.TestEZException;
import com.testez.internal.report.ClassResult;
import com.testez.internal.report.RunResult;
import com.testez.internal.report.MemberResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.testez.internal.clazz.ClassHelper.*;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 12/30/2014
 */
public class TestEZ {
    private static final Logger logger = LoggerFactory.getLogger(TestEZ.class);

    private final EZClass[] allClasses;
    private final EZClass[] testClasses;

    public static void main(String[] args) throws Exception {
        CommandLineOptions clo = new CommandLineOptions();
        JCommander commander = new JCommander(clo, args);
        logger.info("Initializing TestEZ with {}", clo.toString());
        commander.setProgramName("TestEZ");
        commander.setColumnSize(200);
        if (clo.isHelp()) {
            commander.usage();
            return;
        }

        TestEZ testEZ = new TestEZ(clo);
        RunResult result = testEZ.run();

        // TODO: Better reporting...
        if (result.getFailedTests().length > 0) {
            throw new TestEZException(result.toString());
        } else {
            logger.info(result.toString());
        }
    }

    public TestEZ(CommandLineOptions clo) {
        List<String> classes = clo.getClasses();
        List<String> packages = clo.getPackages();
        List<String> suites = clo.getSuites();

        if (classes.isEmpty() && packages.isEmpty() && suites.isEmpty()) {
            throw new RuntimeException(
                    "TestEZ requires at least one of [class, package, or suite]. See --help for more options.");
        }

        allClasses = loadTestEZClasses(getClasses(classes), getClassesByPackages(packages));
        testClasses = Arrays.stream(allClasses).filter(EZClass::isTestClass).toArray(EZClass[]::new);
    }

    public RunResult run() {
        MemberResult[] results = Arrays.stream(testClasses)
                .map(EZClass::run)
                .map(ClassResult::getMemberResults)
                .flatMap(Arrays::stream)
                .toArray(MemberResult[]::new);
        return new RunResult(results);
    }
}
