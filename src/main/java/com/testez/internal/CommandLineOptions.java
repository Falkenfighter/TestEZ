package com.testez.internal;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Justin Graham <Justin.af.graham@gmail.com>
 * @since 12/30/2014
 */
@Parameters
public class CommandLineOptions {

    @Parameter(
            names = {"-class", "-classes"},
            description = "A test class or classes to load. (comma separated)",
            variableArity = true
    )
    private List<String> classes = new ArrayList<>();

    @Parameter(
            names = {"-package", "-packages"},
            description = "A package or packages to recursively load all containing classes. (comma separated)",
            variableArity = true
    )
    private List<String> packages = new ArrayList<>();

    @Parameter(
            names = "-json",
            description = "A json suite descriptor file",
            variableArity = true
    )
    private List<String> suites = new ArrayList<>();

    @Parameter(
            names = {"-help", "--help", "/?"},
            help = true,
            hidden = true
    )
    private boolean help = false;

    @NotNull
    public List<String> getClasses() {
        return classes;
    }

    @NotNull
    public List<String> getPackages() {
        //TODO: save this for later(packages.parallelStream().flatMap(Arrays::stream).toArray(Class[]::new);)
        return packages;
    }

    @NotNull
    public List<String> getSuites() {
        return suites;
    }

    public boolean isHelp() {
        return help;
    }
}
