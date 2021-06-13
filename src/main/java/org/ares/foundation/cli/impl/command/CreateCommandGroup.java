package org.ares.foundation.cli.impl.command;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.template.PackageHandler;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import java.io.Writer;

@Command(name = "make:commandgroup", aliases = "make:cmdgroup",
        description = "Create a command group. There are two methods to create one.\n " +
                "Approach one is to type the name of the command group. This will scaffold a package and class for you automatically.\n" +
                "In the second approach you can specify a different package name as the third parameter.",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:cmdgroup <name> | fo make:commandgroup <name> | fo make:cmdgroup <name> <package> | fo make:commandgroup <name> <package>",
        version = "1.0")
public class CreateCommandGroup implements Runnable, Buildable {

    @Parameters
    private String name;

    @Parameters(defaultValue = "")
    private String subPackageName = "";

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

        System.out.println("PackageName: " + packageName);

        TemplateBuilder templateBuilder = new TemplateBuilder();
        VelocityContext context = buildContext();
        context.put("PACKAGE_NAME", new YamlHandler().getGroupPackageName(PROPERTY_KEY, packageName));

        Writer writer = templateBuilder.createFileWriterGroup(PROPERTY_KEY, packageName, StringUtil.addCommandGroupLabel(name));
        templateBuilder.createTemplate(writer, TEMPLATE, context);
        templateBuilder.flushFileWriter(writer);
    }

}
