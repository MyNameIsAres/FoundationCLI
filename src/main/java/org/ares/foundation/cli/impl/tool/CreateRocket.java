package org.ares.foundation.cli.impl.tool;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.Confirmable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "make:rocket",
        description = "Create a Rocket tool",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:rocket <name>",
        version = "1.0")
public class CreateRocket implements Runnable, Buildable, Confirmable {

    @CommandLine.Parameters()
    private String name;

    final static String PROPERTY_KEY = "tool_location";

    final static String TEMPLATE = "\\tool\\RocketTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addRocketLabel(name));

        return context;
    }

    @Override
    public void confirmSuccessMessage() {
        System.out.println("Successfully created a new Rocket class!");
    }

    @Override
    public void run() {
        new TemplateBuilder(PROPERTY_KEY, StringUtil.addRocketLabel(name), TEMPLATE, buildContext()).buildCommand();

        confirmSuccessMessage();
    }

}