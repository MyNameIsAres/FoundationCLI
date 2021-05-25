package org.ares.foundation.cli.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TemplateBuilder {

    YamlHandler yamlHandler = new YamlHandler();

    public Writer createFileWriter(String propertyKey, String name) {
        try {
            return new FileWriter(yamlHandler.getTargetLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey), name) + ".java");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

        return null;
    }

    public Writer createFileWriterGroup(String propertyKey, String commandGroupPath, String name) {
        try {
            return new FileWriter(yamlHandler.getTargetLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey) + "/" + commandGroupPath , name) + ".java");
        } catch (IOException | NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public void flushFileWriter(Writer writer) {
        try {
            writer.flush();
            writer.close();
        } catch (IOException | NullPointerException exception) {
           System.out.println("We couldn't flush this due to an exception!");
        }
    }

    public void createTemplate(Writer writer, String template, VelocityContext context) {
        VelocityEngine engine = new VelocityBuilder().createVelocityEngineFoundation();
        Template templateName = engine.getTemplate(template);

        try {
            engine.mergeTemplate(templateName.getName(), "UTF-8", context, writer);
        } catch(NullPointerException exception) {
          // TODO: Create folder if it doesn't exist.

            System.out.println("We can't find this directory!");
        }

    }
}
