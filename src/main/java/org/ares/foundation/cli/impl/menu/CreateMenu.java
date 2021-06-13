package org.ares.foundation.cli.impl.menu;

import org.apache.velocity.VelocityContext;

import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.Writer;

@Command(name = "make:menu",
        description = "Create a custom menu",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:menu <name>",
        version = "1.0")
public class CreateMenu implements Runnable, Buildable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "menu_location";

    final static String TEMPLATE = "\\menu\\MenuTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addMenuLabel(name));

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
