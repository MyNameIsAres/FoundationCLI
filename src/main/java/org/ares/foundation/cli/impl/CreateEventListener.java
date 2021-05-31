package org.ares.foundation.cli.impl;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.Buildable;
import org.ares.foundation.cli.util.*;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.Writer;

@Command(name = "make:listener", aliases = "make:l")
public class CreateEventListener implements Runnable, Buildable {

    @Parameters()
    private String name = "";

    @Parameters(defaultValue = "")
    private final String eventType = "";

    final static String PROPERTY_KEY = "listener_location";

    final static String TEMPLATE_FOLDER = "\\listeners\\";

    final String template = TEMPLATE_FOLDER;

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addListenerLabel(name));

        return context;
    }

    @Override
    public void run() {
        TemplateBuilder templateBuilder = new TemplateBuilder();
        Writer writer = templateBuilder.createFileWriter(PROPERTY_KEY, StringUtil.addListenerLabel(name));
        templateBuilder.createTemplate(writer, template + getEventType(eventType), buildContext());
        templateBuilder.flushFileWriter(writer);
    }

    public String getEventType(String eventType) {
        return new EventTemplateHandler().fetchTemplate(eventType);
    }

}