package org.ares.foundation.cli.impl.orion;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.Buildable;
import org.ares.foundation.cli.util.StringUtil;
import org.ares.foundation.cli.util.TemplateBuilder;
import org.ares.foundation.cli.util.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.Writer;

@Command(name = "make:rank")
public class CreateRank implements Runnable, Buildable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "rank_location";

    final static String TEMPLATE = "\\Orion\\RankTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addRankLabel(name));
        context.put("NAME", StringUtil.getRankName(name));
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
