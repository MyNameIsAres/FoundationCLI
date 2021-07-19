package org.ares.foundation.cli.util.template;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TemplateBuilder {

    private final String propertyKey;
    private final String name;
    private final String template;
    private final VelocityContext context;
    private String subPackageName;

    private final YamlHandler yamlHandler = new YamlHandler();

    public TemplateBuilder(String propertyKey, String name, String template, VelocityContext context) {
        this.propertyKey = propertyKey;
        this.name = name;
        this.template = template;
        this.context = context;
    }

    public String getSubPackageName() {
        return subPackageName.toLowerCase();
    }

    public void setSubPackageName(String packageName) {
        this.subPackageName = packageName;
    }

    public void buildCommand() {
        final Writer writer = this.createFileWriter(propertyKey, name);
        this.createTemplate(writer, template, context);
        this.flushFileWriter(writer);
    }

    // TODO Consider to clean this code up
    public void buildSubCommandGroup() {
        Writer writer;
        if (!subPackageExists(propertyKey, getSubPackageName())) {
            writer = this.createFileWriter(propertyKey, name);
            context.put("PACKAGE_NAME", yamlHandler.getPackageName(propertyKey));
        } else {
            writer = this.createFileWriterGroup(propertyKey, getSubPackageName(), name);
            context.put("PACKAGE_NAME", yamlHandler.getGroupPackageName(propertyKey, getSubPackageName()));
        }
        this.createTemplate(writer, template, context);
        this.flushFileWriter(writer);
    }

    public void buildCommandGroup() {
        final Writer writer = this.createFileWriterGroup(propertyKey, getSubPackageName(), name);
        this.createTemplate(writer, template, context);
        this.flushFileWriter(writer);
    }

    private Writer createFileWriter(String propertyKey, String name) {
        try {
            System.out.println(yamlHandler.getTargetLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey), name) + ".java");
            return new FileWriter(yamlHandler.getTargetLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey), name) + ".java");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

        return null;
    }

    private Writer createFileWriterGroup(String propertyKey, String commandGroupPath, String name) {
        try {
            return new FileWriter(yamlHandler.getTargetLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey) + "/" + commandGroupPath , name) + ".java");
        } catch (IOException | NullPointerException exception) {
            System.out.println("A NullPointerException occurred!");
        }

        System.out.println("We gonna return null!");

        return null;
    }

    private void flushFileWriter(Writer writer) {
        try {
            writer.flush();
            writer.close();
        } catch (IOException | NullPointerException exception) {
           System.out.println("We couldn't flush this due to an exception!");
        }
    }

    private void createTemplate(Writer writer, String template, VelocityContext context) {
        VelocityEngine engine = new VelocityBuilder().createVelocityEngineFoundation();
        Template templateName = engine.getTemplate(template);

        System.out.println("Writer: " + writer.toString());

        try {
            engine.mergeTemplate(templateName.getName(), "UTF-8", context, writer);
        } catch(NullPointerException exception) {
            exception.printStackTrace();

            System.out.println("We can't find this directory!");
        }
    }

    private boolean subPackageExists(String propertyKey, String subPackageName) {
        final boolean PATH_TO_SEARCH = Files.exists(Paths.get("src/main/java/" + new YamlHandler().getRawGroupPackageName(propertyKey, subPackageName)));
        return subPackageName.equals("") || PATH_TO_SEARCH;
    }
}
