package org.ares.foundation.cli.util.template;

import org.ares.foundation.cli.util.string.StringUtil;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PackageHandler {

    public static String createPackage(String name, String subPackageName, String propertyKey)  {
        if (subPackageName.isEmpty()) {
            String packageName = StringUtil.getCommandGroupName(name).toLowerCase();

            createDirectoryWithPrefix(propertyKey, packageName);
            return packageName;

        } else {
            createDirectoryWithPackageName(propertyKey, subPackageName);
            return subPackageName;
        }
    }

    private static void createDirectoryWithPrefix(String propertyKey, String packageName) {
        YamlHandler yamlHandler = new YamlHandler();

        try {
            Files.createDirectory(Paths.get(yamlHandler.getCommandGroupLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey))
                    + packageName));
        } catch (IOException exception) {
            System.out.println("An error occurred while creating a directory! Please check the stack trace:");
            exception.printStackTrace();
        }

    }

    private static void createDirectoryWithPackageName(String propertyKey, String subPackageName) {
        YamlHandler yamlHandler = new YamlHandler();

        try {
            Files.createDirectory(Paths.get(yamlHandler.getCommandGroupLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey))
                    + subPackageName));
        } catch (IOException exception) {
            System.out.println("An error occurred while creating a directory! Please check the stack trace:");
            exception.printStackTrace();

            System.out.println();
            System.out.println("The folder probably already exists.");
        }

    }

}
