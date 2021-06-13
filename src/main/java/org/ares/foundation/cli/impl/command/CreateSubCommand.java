package org.ares.foundation.cli.impl.command;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import java.io.*;

@Command(name = "make:subcommand", aliases = "make:subcmd",
        description = "Create a sub-command for a command group. When you don't fill in a valid package name or a missing package\n" +
                "we create a regular class. Note that in the current project version (1.0) this creates an invalid command project path.\n" +
                "That is going to be resolved in a future update along with a better architecture for command groups and sub-commands.",

        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:subcommand <name> <package> | fo make:subcmd <name> <package>",
        version = "1.0")
public class CreateSubCommand implements Runnable, Buildable {

    @Parameters()
    private String name;

    @Parameters(defaultValue = "")
    private String subPackageName = "";

    final static String PROPERTY_KEY = "command_location";

    final static String TEMPLATE = "\\command\\SimpleSubCommandTemplate.vm";

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", "");
        context.put("CLASS_NAME", StringUtil.addCommandLabel(name));
        context.put("NAME", StringUtil.getCommandName(name).toLowerCase());

        return context;
    }

    @Override
    public void run() {
        TemplateBuilder templateBuilder = new TemplateBuilder();
        VelocityContext context = buildContext();
        context.put("PACKAGE_NAME", new YamlHandler().getGroupPackageName(PROPERTY_KEY, subPackageName));

        Writer writer = templateBuilder.createFileWriter(PROPERTY_KEY, StringUtil.addCommandLabel(name));
        templateBuilder.createTemplate(writer, TEMPLATE, context);
        templateBuilder.flushFileWriter(writer);
    }
}