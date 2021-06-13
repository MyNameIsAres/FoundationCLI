package org.ares.foundation.cli.impl.orion.boss;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.util.Buildable;
import org.ares.foundation.cli.util.string.StringUtil;
import org.ares.foundation.cli.util.template.TemplateBuilder;
import org.ares.foundation.cli.util.template.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.Writer;

@Command(name = "make:npc",
        description = "Create a NPC from Project Orion",
        mixinStandardHelpOptions = true,
        customSynopsis = "fo make:npc <name>",
        version = "1.0")
public class CreateNPC implements Runnable, Buildable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "npc_location";

    final static String TEMPLATE = "\\Orion\\Boss\\NpcTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", name);
        context.put("NAME", StringUtil.getNPCName(name));

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
