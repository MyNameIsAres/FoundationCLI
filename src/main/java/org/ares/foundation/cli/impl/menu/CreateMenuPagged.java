package org.ares.foundation.cli.impl.menu;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.Confirmable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:menupagged", aliases = "make:menup",
        description = "Create a paged menu",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:menupagged <name> | fo make:menup <name>",
        version = "1.0")
public class CreateMenuPagged implements Runnable, Buildable, Confirmable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "menu_location";

    final static String TEMPLATE = "\\menu\\MenuToolsTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addCommandLabel(name));
        context.put("TYPE", "T");
        context.put("TYPE_VARIABLE", "t");

        return context;
    }

    @Override
    public void confirmSuccessMessage() {
        System.out.println("Successfully created a new MenuPagged class!");
    }

    @Override
    public void run() {
        new TemplateBuilder(PROPERTY_KEY, StringUtil.addMenuLabel(name), TEMPLATE, buildContext()).buildCommand();

        confirmSuccessMessage();
    }
}

