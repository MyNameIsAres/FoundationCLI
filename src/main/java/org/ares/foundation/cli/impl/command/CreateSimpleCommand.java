package org.ares.foundation.cli.impl.command;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:command", aliases = "make:cmd",
        description = "Create a custom SimpleCommand Command. By convention we create a command in the following format:\n" +
                "<name>Command | BossCommand",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:command <name> | fo make:cmd <name>",
        version = "1.0")
public class CreateSimpleCommand implements Runnable, Buildable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "command_location";

    final static String TEMPLATE = "\\command\\SimpleCommandTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addCommandLabel(name));
        context.put("NAME", StringUtil.getCommandName(name));

        return context;
    }

    @Override
    public void run() {
      new TemplateBuilder(PROPERTY_KEY, name, TEMPLATE, buildContext()).buildCommand();
    }
}
