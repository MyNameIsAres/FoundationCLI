package org.ares.foundation.cli.impl.command;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.Buildable;
import org.ares.foundation.cli.util.PackageHandler;
import org.ares.foundation.cli.util.StringUtil;
import org.ares.foundation.cli.util.TemplateBuilder;
import org.ares.foundation.cli.util.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import java.io.Writer;

@Command(name = "make:commandgroup", aliases = "make:cmdgroup")
public class CreateCommandGroup implements Runnable, Buildable {

    @Parameters
    private String name;

    @Parameters(defaultValue = "")
    private final String subPackageName = "";

    final static String PROPERTY_KEY = "command_location";

    final static String TEMPLATE = "\\command\\SimpleCommandGroupTemplate.vm";

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", "");
        context.put("CLASS_NAME", StringUtil.addCommandGroupLabel(name));

        return context;
    }

    @Override
    public void run() {
        final String packageName =  PackageHandler.createPackage(name, subPackageName, PROPERTY_KEY);
        TemplateBuilder templateBuilder = new TemplateBuilder();
        VelocityContext context = buildContext();
        context.put("PACKAGE_NAME", new YamlHandler().getGroupPackageName(PROPERTY_KEY, packageName));

        Writer writer = templateBuilder.createFileWriterGroup(PROPERTY_KEY, packageName, name);
        templateBuilder.createTemplate(writer, TEMPLATE, context);
        templateBuilder.flushFileWriter(writer);
    }

}
