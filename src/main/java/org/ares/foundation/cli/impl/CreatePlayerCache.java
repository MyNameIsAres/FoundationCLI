package org.ares.foundation.cli.impl;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.Confirmable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:cache",
        description = "Create a standard cache class. By default we assume you want to scaffold a PlayerCache class.",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:cache <name>",
        version = "1.0")
public class CreatePlayerCache implements Runnable, Buildable, Confirmable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "cache_location";

    final static String TEMPLATE = "\\PlayerCacheTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addCacheLabel(name));
        context.put("NAME", StringUtil.addCacheLabel(name));

        return context;
    }

    @Override
    public void confirmSuccessMessage() {
        System.out.println("Successfully created a new Cache class!");
    }

    @Override
    public void run() {
        new TemplateBuilder(PROPERTY_KEY, StringUtil.addCacheLabel(name), TEMPLATE, buildContext()).buildCommand();

        confirmSuccessMessage();
    }
}
