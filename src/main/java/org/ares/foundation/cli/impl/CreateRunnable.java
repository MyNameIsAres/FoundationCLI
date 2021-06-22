package org.ares.foundation.cli.impl;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:runnable",
        description = "Create a Runnable",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:runnable <name>",
        version = "1.0")
public class CreateRunnable implements Runnable, Buildable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "runnable_location";

    final static String TEMPLATE = "\\RunnableTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addTaskLabel(name));

        return context;
    }

    @Override
    public void run() {
        new TemplateBuilder(PROPERTY_KEY, name, TEMPLATE, buildContext()).buildCommand();
    }
}

