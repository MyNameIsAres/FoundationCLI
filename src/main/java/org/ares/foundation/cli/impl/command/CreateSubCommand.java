package org.ares.foundation.cli.impl.command;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.Confirmable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:subcommand", aliases = "make:subcmd",
        description = "Create a sub-command for a command group. When you don't fill in a valid package name or a missing package\n" +
                "we create a regular class. Note that in the current project version (1.0) this creates an invalid command project path.\n" +
                "That is going to be resolved in a future update along with a better architecture for command groups and sub-commands.",

        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:subcommand <name> <package> | fo make:subcmd <name> <package>",
        version = "1.0")
public class CreateSubCommand implements Runnable, Buildable, Confirmable {

    @Parameters()
    private String name;

    @Parameters(defaultValue = "")
    private String subPackageName;

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
    public void confirmSuccessMessage() {
        System.out.println("Successfully created a new Sub-Command class!");
    }

    @Override
    public void run() {
        TemplateBuilder templateBuilder = new TemplateBuilder(PROPERTY_KEY, StringUtil.addCommandLabel(name), TEMPLATE, buildContext());
        templateBuilder.setSubPackageName(subPackageName);
        templateBuilder.buildSubCommandGroup();

        confirmSuccessMessage();
    }
}