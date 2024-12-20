package com.plugin;

public class DependencyHandlerFactory {

    public static DependencyHandler getHandler(String fileType) {
        if (fileType.equalsIgnoreCase("json")) return new JsonDependencyHandler();
        if (fileType.equalsIgnoreCase("properties")) return new PropertiesDependencyHandler();
        return null;
    }
}
