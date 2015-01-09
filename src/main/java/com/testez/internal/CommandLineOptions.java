package com.testez.internal;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.common.base.MoreObjects;
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
    private List<String> classes;

    @Parameter(
            names = {"-package", "-packages"},
            description = "A package or packages to recursively load all containing classes. (comma separated)",
            variableArity = true
    )
    private List<String> packages;

    @Parameter(
            names = "-json",
            description = "A json suite descriptor file",
            variableArity = true
    )
    private List<String> suites;

    @Parameter(
            names = {"-help", "--help", "/?"},
            help = true,
            hidden = true
    )
    private boolean help = false;

    @NotNull
    public List<String> getClasses() {
        return (classes == null) ? new ArrayList<>(0) : classes;
    }

    @NotNull
    public List<String> getPackages() {
        return (packages == null) ? new ArrayList<>(0) : packages;
    }

    @NotNull
    public List<String> getSuites() {
        return (suites == null) ? new ArrayList<>(0) : suites;
    }

    public boolean isHelp() {
        return help;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("classes", classes)
                .add("packages", packages)
                .add("suites", suites)
                .toString();
    }
}
