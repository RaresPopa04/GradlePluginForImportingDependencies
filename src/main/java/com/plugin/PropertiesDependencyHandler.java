package com.plugin;

import org.gradle.api.Project;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class PropertiesDependencyHandler extends DependencyHandler {
    @Override
    public void addDependencies(Project project, File dependencyFile) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(dependencyFile));

        properties.forEach((key, value) -> {
            String configurationName = (String) key;
            Arrays.stream(((String) value).split(",")).forEach(dependencyNotation -> {
                project.getLogger().lifecycle("Adding dependency: " + dependencyNotation + " to configuration: " + configurationName);
                project.getDependencies().add(configurationName, dependencyNotation);
            });

        });
    }
}
