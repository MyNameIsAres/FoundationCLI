package org.ares.foundation.cli.impl.orion;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.Writer;

@Command(name = "make:quest",
        description = "Create a Quest class from project Orion",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:quest <name>",
        version = "1.0")
public class CreateQuest implements Runnable, Buildable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "quest_location";

    final static String TEMPLATE = "\\Orion\\QuestTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addQuestLabel(name));
        context.put("NAME", StringUtil.getQuestName(name));

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
