# Gradle Plugin for Parsing External Dependencies

## Overview

This Gradle plugin allows you to manage project dependencies from an external file. The plugin supports two file formats: **JSON** and **PROPERTIES**. It parses the dependencies defined in the file and applies them to your project automatically.

### Key Features:

- Supports JVM-only projects with a single-module structure.
- Parses dependencies from external files.
- Compatible with both JSON and PROPERTIES file formats.
- Simplifies dependency management for large projects.

---

## Installation

### Step 1: Add the Plugin to `buildSrc`

To include the plugin in your project, place it in the `buildSrc` folder of your Gradle project. Follow these steps:

1. Create a `buildSrc` directory in the root of your project.
2. Inside the `buildSrc` directory, copy the plugin's folder contents.

### Step 2: Apply the Plugin

Add the plugin to your `build.gradle` file:

```groovy
plugins {
    id 'java'
    id 'com.plugin.dependencyPlugin'
}
```

### Step 3: Configure the Plugin

Add the `dependencyData` block in your `build.gradle` file to configure the plugin:

```groovy
dependencyData {
    fileType = 'properties' // Supported values: 'json', 'properties'
    dependencyFile = file('dependencies.properties') // Path to your dependency file
}

```

or

```groovy
dependencyData {
    fileType = 'json' // Supported values: 'json', 'properties'
    dependencyFile = file('dependencies.json') // Path to your dependency file
}
```


---

## Dependency File Structure

### JSON Format

Create a `dependencies.json` file with the following structure:

```json
{
  "implementation": ["com.google.guava:guava:31.1-jre", "org.apache.commons:commons-lang3:3.12.0"],
  "compileOnly": ["javax.annotation:javax.annotation-api:1.3.2", "org.projectlombok:lombok:1.18.28"],
  "runtimeOnly": ["mysql:mysql-connector-java:8.0.34"],
  "testImplementation": ["org.junit.jupiter:junit-jupiter:5.9.3"],
  "testCompileOnly": ["org.mockito:mockito-core:5.3.1"],
  "testRuntimeOnly": ["org.hamcrest:hamcrest:2.2"],
  "annotationProcessor": ["org.projectlombok:lombok:1.18.28", "org.mapstruct:mapstruct-processor:1.5.0.Final"]
}
```

### PROPERTIES Format

Alternatively, create a `depe.properties` file with the following structure:

```properties
implementation=com.google.guava:guava:31.1-jre,org.apache.commons:commons-lang3:3.12.0
compileOnly=javax.annotation:javax.annotation-api:1.3.2,org.projectlombok:lombok:1.18.28
runtimeOnly=mysql:mysql-connector-java:8.0.34
testImplementation=org.junit.jupiter:junit-jupiter:5.9.3
testCompileOnly=org.mockito:mockito-core:5.3.1
testRuntimeOnly=org.hamcrest:hamcrest:2.2
annotationProcessor=org.projectlombok:lombok:1.18.28,org.mapstruct:mapstruct-processor:1.5.0.Final
```

---

## Usage

### Adding Dependencies

1. Place the external dependency file (e.g., `dependencies.json` or `dependencies.properties`) in your project directory.
2. Ensure the `dependencyData` block in your `build.gradle` file points to the correct file type and path.
3. Run any Gradle task, such as `build`, to trigger the plugin. The plugin will parse the file and add the dependencies to your project.

### Example Output

When processing dependencies, you will see log messages like this:

```
Adding dependency: com.google.guava:guava:31.1-jre to configuration: implementation
Adding dependency: org.apache.commons:commons-lang3:3.12.0 to configuration: implementation
```