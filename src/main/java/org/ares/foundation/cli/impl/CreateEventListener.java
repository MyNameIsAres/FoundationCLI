package org.ares.foundation.cli.impl;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.*;
import org.ares.foundation.cli.util.events.EventTemplateHandler;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:listener", aliases = "make:l",
        description = "Create an event listener! There are different event type options available: <wiki> ",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:listener <name> | fo make:listener <name> <eventType>",
        version = "1.0")
public class CreateEventListener implements Runnable, Buildable, Confirmable {

    @Parameters()
    private String name;

    @Parameters(defaultValue = "")
    private String eventType;

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
    public void confirmSuccessMessage() {
        System.out.println("Successfully created a new Event Listener class!");
    }

    private String getEventType(String eventType) {
        return new EventTemplateHandler().fetchTemplate(eventType);
    }

    @Override
    public void run() {
         new TemplateBuilder(PROPERTY_KEY, StringUtil.addListenerLabel(name), template + getEventType(eventType), buildContext()).buildCommand();

         confirmSuccessMessage();
    }


}