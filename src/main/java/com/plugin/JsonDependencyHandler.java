package com.plugin;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gradle.api.Project;

import java.io.File;
import java.io.IOException;

public class JsonDependencyHandler extends DependencyHandler {
    @Override
    public void addDependencies(Project project, File dependencyFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(dependencyFile);

        jsonNode.fields().forEachRemaining(dependency -> {
            String configurationName = dependency.getKey();

            dependency.getValue().forEach(dependencyNotation -> {
                project.getLogger().lifecycle("Adding dependency: " + dependencyNotation + " to configuration: " + configurationName);
                project.getDependencies().add(configurationName, dependencyNotation.asText());
            });
        });
    }

}
