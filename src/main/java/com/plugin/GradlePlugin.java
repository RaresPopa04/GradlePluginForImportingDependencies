package com.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.File;
import java.io.IOException;

public class GradlePlugin implements Plugin<Project> {
    @Override
    public void apply(Project target) {
        target.getLogger().lifecycle("Hello from GradlePlugin!");
        target.afterEvaluate(project ->
        {
            DependencyData dependencyData = project.getExtensions().getByType(DependencyData.class);

            File dependencyFile = new File(dependencyData.getDependencyFile());

            if (!dependencyFile.exists())
                throw new IllegalArgumentException("No dependency file found: " + dependencyFile.getAbsolutePath());


            DependencyHandler dependencyHandler = DependencyHandlerFactory.getHandler(dependencyData.getFileType());
            if (dependencyHandler == null)
                throw new IllegalArgumentException("Unsupported file type: " + dependencyData.getFileType());

            try {
                dependencyHandler.addDependencies(project, dependencyFile);
            } catch (IOException e) {
                throw new RuntimeException("Error while parsing the file " + e);
            }
        });

    }
}
