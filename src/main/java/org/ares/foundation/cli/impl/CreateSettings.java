package org.ares.foundation.cli.impl;

import org.apache.velocity.VelocityContext;
import org.ares.foundation.cli.Buildable;
import org.ares.foundation.cli.util.StringUtil;
import org.ares.foundation.cli.util.TemplateBuilder;
import org.ares.foundation.cli.util.YamlHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.Writer;

@Command(name = "make:settings")
public class CreateSettings implements Runnable, Buildable {

    @Parameters()
    private String name;

    final static String PROPERTY_KEY = "settings_location";

    final static String TEMPLATE = "\\SettingsTemplate.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addSettingsLabel(name));

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
