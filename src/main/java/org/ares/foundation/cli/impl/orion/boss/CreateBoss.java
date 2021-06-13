package org.ares.foundation.cli.impl.orion.boss;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.Writer;

@Command(name = "make:boss",
        description = "Create a custom Boss from project Orion",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:boss <name>",
        version = "1.0")
public class CreateBoss implements Runnable, Buildable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "boss_location";

    final static String TEMPLATE = "\\Orion\\Boss\\BossTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addBossLabel(name));
        context.put("NAME", StringUtil.getBossName(name));

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
