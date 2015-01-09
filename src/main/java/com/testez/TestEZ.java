package com.testez;

import com.beust.jcommander.JCommander;
import com.testez.internal.CommandLineOptions;
import com.testez.internal.RunResult;
import com.testez.internal.TestEZClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.testez.internal.ClassHelper.*;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 12/30/2014
 */
public class TestEZ {
    private static final Logger logger = LoggerFactory.getLogger(TestEZ.class);

    private final TestEZClass[] allClasses;
    private final TestEZClass[] testClasses;

    public static void main(String[] args) {
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
        testEZ.run();
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
        testClasses = Arrays.stream(allClasses).filter(TestEZClass::isTestClass).toArray(TestEZClass[]::new);
    }

    public RunResult run() {
        Arrays.stream(testClasses).forEach(TestEZClass::run);
        return null;
    }
}
