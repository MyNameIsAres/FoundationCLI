package org.ares.foundation.cli.impl.command;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.Buildable;
import org.ares.foundation.cli.util.StringUtil;
import org.ares.foundation.cli.util.TemplateBuilder;
import org.ares.foundation.cli.util.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.Writer;

@Command(name = "make:command", aliases = "make:cmd")
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
        TemplateBuilder templateBuilder = new TemplateBuilder();
        Writer writer = templateBuilder.createFileWriter(PROPERTY_KEY, name);
        templateBuilder.createTemplate(writer, TEMPLATE, buildContext());
        templateBuilder.flushFileWriter(writer);
    }
}
