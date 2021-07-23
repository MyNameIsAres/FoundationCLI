package org.ares.foundation.cli.impl;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.Confirmable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:enchantment", aliases = "make:ec",
        description = "Create a SimpleEnchant enchantment.",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:enchantment <name> | fo make:ec <name>",
        version = "1.0")
public class CreateSimpleEnchantment implements Runnable, Buildable, Confirmable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "enchant_location";

    final static String TEMPLATE = "\\SimpleEnchantmentTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addEnchantmentLabel(name));

        return context;
    }

    @Override
    public void confirmSuccessMessage() {
        System.out.println("Successfully created a new SimpleEnchantment class!");
    }

    @Override
    public void run() {
        new TemplateBuilder(PROPERTY_KEY, name, TEMPLATE, buildContext()).buildCommand();

        confirmSuccessMessage();
    }
}
