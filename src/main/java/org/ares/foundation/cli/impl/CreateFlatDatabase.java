package org.ares.foundation.cli.impl;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.Confirmable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:flatdatabase", aliases = "make:flatdb",
        description = "Create a SimpleFlatDatabase with a given type. If the type is invalid, we insert a generic 'T' parameter.",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:flatdatabase <name> <type> | fo make:flatdb <name> <type>",
        version = "1.0")
public class CreateFlatDatabase implements Runnable, Buildable, Confirmable {

    @Parameters()
    private String name;

    @Parameters(defaultValue = "T")
    private String type;

    final static String PROPERTY_KEY = "database_location";

    final static String TEMPLATE = "\\SimpleFlatDatabaseTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addDatabaseLabel(name));
        context.put("TYPE", type);
        context.put("TYPE_PARAM", "t");

        return context;
    }

    @Override
    public void confirmSuccessMessage() {
        System.out.println("Successfully created a new SimpleFlatDatabase class!");
    }

    @Override
    public void run() {
        new TemplateBuilder(PROPERTY_KEY,  StringUtil.addDatabaseLabel(name), TEMPLATE, buildContext()).buildCommand();

        confirmSuccessMessage();
    }
}
