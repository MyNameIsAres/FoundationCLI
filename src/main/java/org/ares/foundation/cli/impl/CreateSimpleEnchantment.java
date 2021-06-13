package org.ares.foundation.cli.impl;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.Writer;

@Command(name = "make:enchantment", aliases = "make:ec",
        description = "Create a SimpleEnchant enchantment.",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:enchantment <name> | fo make:ec <name>",
        version = "1.0")
public class CreateSimpleEnchantment implements Runnable, Buildable {

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
    public void run() {
        TemplateBuilder templateBuilder = new TemplateBuilder();
        Writer writer = templateBuilder.createFileWriter(PROPERTY_KEY, name);
        templateBuilder.createTemplate(writer, TEMPLATE, buildContext());
        templateBuilder.flushFileWriter(writer);
    }
}
