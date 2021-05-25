package org.ares.foundation.cli.util;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class YamlHandler {

    final static String PREFIX = "src/main/java/";

    public Map<String, Object> fetchInformation() {

        InputStream inputStream;
        Map<String, Object> data;
        try {
            inputStream = new FileInputStream(
                    "op-config.yml"
            );

            Yaml yaml = new Yaml();
            data = yaml.load(inputStream);

            return data;

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        
        return null;
    }

    public String getProjectPath() {
        verifyKey("project-path");

        return fetchInformation().get("project-path").toString();
    }

    public String getKeyValue(String key) {

        return fetchInformation().get(key).toString();
    };

    // TODO Uhh, fix this yeh
    public void verifyKey(String key)  {
        if (this.getKeyValue(key) == null || this.getKeyValue(key).isEmpty()) {

        }

    }

    public String getTargetLocation(final String projectPath, final String key, final String name) {
        return  PREFIX + projectPath + key + "/" + name;
    }

    public String getCommandGroupLocation(final String projectPath, final String key) {
        System.out.println(projectPath);
        System.out.println(key);
        return PREFIX + projectPath + key + "/";
    }


    public String getPackageName(String key) {
        String packageName = this.getProjectPath() + this.getKeyValue(key);
        packageName = packageName.replace("/", ".");

        return packageName;
    }

    public String getGroupPackageName(String key, String packageName) {
        String newPackageName = this.getProjectPath() + this.getKeyValue(key) + "." +packageName;
        newPackageName = newPackageName.replace("/", ".");

        return newPackageName;
    }
}
