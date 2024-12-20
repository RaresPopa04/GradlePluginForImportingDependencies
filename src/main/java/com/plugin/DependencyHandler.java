package com.plugin;

import org.gradle.api.Project;

import java.io.File;
import java.io.IOException;

public abstract class DependencyHandler {

    public abstract void addDependencies(Project project, File dependencyFile) throws IOException;
}
