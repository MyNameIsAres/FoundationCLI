package org.ares.foundation.cli.impl.orion;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:class",
        description = "Create a Class (model) from project Orion",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:command <name> | fo make:cmd <name>",
        version = "1.0")
public class CreateClass implements Runnable, Buildable {

    @Parameters
    private String name;

    final static String PROPERTY_KEY = "class_location";

    final static String TEMPLATE = "\\Orion\\ClassTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addClassLabel(name));
        context.put("NAME", StringUtil.getClassName(name));

        return context;
    }

    @Override
    public void run() {
        new TemplateBuilder(PROPERTY_KEY, name, TEMPLATE, buildContext()).buildCommand();
    }

}
