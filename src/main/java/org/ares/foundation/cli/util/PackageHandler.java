package org.ares.foundation.cli.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PackageHandler {

    public static String createPackage(String name, String subPackageName, String propertyKey)  {
        if (subPackageName.isEmpty()) {
            String packageName = StringUtil.getCommandGroupName(name).toLowerCase();

            createDirectoryWithPrefix(propertyKey, subPackageName);

            return packageName;
        } else {
            createDirectoryWithPackageName(propertyKey, subPackageName);

            return subPackageName;
        }
    }

    public static String createPackageSubCommand(String name, String subPackageName, String propertyKey)  {
        if (subPackageName.isEmpty()) {
            String packageName = StringUtil.getCommandName(name).toLowerCase();

            createSubDirectoryWithPrefix(propertyKey, subPackageName);

            return packageName;
        } else {
            createSubDirectoryWithPackageName(propertyKey, subPackageName);
            return subPackageName;
        }
    }

    private static void createDirectoryWithPrefix(String propertyKey, String packageName) {
        YamlHandler yamlHandler = new YamlHandler();

        try {
            Files.createDirectory(Paths.get(yamlHandler.getCommandGroupLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey))
                    + packageName));
        } catch (Exception exception) {
            System.out.println("An error occurred while creating a directory! Please check the stack trace:");
            exception.printStackTrace();
        }

    }

    private static void createDirectoryWithPackageName(String propertyKey, String subPackageName) {
        YamlHandler yamlHandler = new YamlHandler();

        try {
            Files.createDirectory(Paths.get(yamlHandler.getCommandGroupLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey))
                    + subPackageName));
        } catch (Exception exception) {
            System.out.println("An error occurred while creating a directory! Please check the stack trace:");
            exception.printStackTrace();
        }

    }

    private static void createSubDirectoryWithPrefix(String propertyKey, String packageName) {
        YamlHandler yamlHandler = new YamlHandler();

        try {
            Files.createDirectory(Paths.get(yamlHandler.getCommandGroupLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey))
                    + packageName));
        } catch (Exception exception) {
            System.out.println("An error occurred while creating a directory! Please check the stack trace:");
            exception.printStackTrace();
        }

    }

    private static void createSubDirectoryWithPackageName(String propertyKey, String subPackageName) {
        YamlHandler yamlHandler = new YamlHandler();

        try {
            Files.createDirectory(Paths.get(yamlHandler.getCommandGroupLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey))
                    + subPackageName));
        } catch (Exception exception) {
            System.out.println("An error occurred while creating a directory! Please check the stack trace:");
            exception.printStackTrace();
        }

    }
}
