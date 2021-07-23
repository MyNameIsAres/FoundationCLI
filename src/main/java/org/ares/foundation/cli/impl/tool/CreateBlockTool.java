package org.ares.foundation.cli.impl.tool;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.Confirmable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:blocktool",
        description = "Create a BlockTool tool",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:blocktool <name>",
        version = "1.0")
public class CreateBlockTool implements Runnable, Buildable, Confirmable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "tool_location";

    final static String TEMPLATE = "\\tool\\BlockToolTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addBlockToolLabel(name));

        return context;
    }

    @Override
    public void confirmSuccessMessage() {
        System.out.println("Successfully created a new BlockTool class!");
    }

    @Override
    public void run() {
        new TemplateBuilder(PROPERTY_KEY, StringUtil.addBlockToolLabel(name), TEMPLATE, buildContext()).buildCommand();

        confirmSuccessMessage();
    }

}
