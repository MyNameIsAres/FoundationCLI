package org.ares.foundation.cli;

import org.ares.foundation.cli.impl.*;
import org.ares.foundation.cli.impl.command.CreateCommandGroup;
import org.ares.foundation.cli.impl.command.CreateSimpleCommand;
import org.ares.foundation.cli.impl.command.CreateSubCommand;
import org.ares.foundation.cli.impl.conversation.CreateSimpleConversation;
import org.ares.foundation.cli.impl.conversation.CreateSimplePrompt;
import org.ares.foundation.cli.impl.menu.CreateMenu;
import org.ares.foundation.cli.impl.menu.CreateMenuPagged;
import org.ares.foundation.cli.impl.menu.CreateMenuQuantifiable;
import org.ares.foundation.cli.impl.menu.CreateMenuTools;
import org.ares.foundation.cli.impl.orion.CreateClass;
import org.ares.foundation.cli.impl.orion.CreateQuest;
import org.ares.foundation.cli.impl.orion.CreateRank;
import org.ares.foundation.cli.impl.orion.boss.CreateBoss;
import org.ares.foundation.cli.impl.orion.boss.CreateBossSkill;
import org.ares.foundation.cli.impl.orion.boss.CreateNPC;
import org.ares.foundation.cli.impl.tool.CreateBlockTool;
import org.ares.foundation.cli.impl.tool.CreateRocket;
import org.ares.foundation.cli.impl.tool.CreateTool;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command
public class FoundationCLI implements Runnable{

    public static void main(String[] args) {
        disableAnsi();

        new FoundationCLI().createCommands(args);
    }

    @Override
    public void run() {
        welcomeMessage();
    }

    private void welcomeMessage() {
        System.out.println("==============================");
        System.out.println("Welcome to FoundationCLI!");
        System.out.println();
        System.out.println("Please check the Wiki below for a complete list of all possible commands.");
        System.out.println("Join the community on https://www.skool.com/project-orion");
        System.out.println();
        System.out.println("This project is still in development. Please report any issues here: \n" +
                "https://github.com/MyNameIsAres/FoundationCLI/issues");
        System.out.println("");
        System.out.println("Check the complete Wiki for this project here: \n" + "https://github.com/MyNameIsAres/FoundationCLI-JAR/wiki");
        System.out.println("");
        System.out.println("==============================");
    }

    private static void disableAnsi() {
        System.setProperty("picocli.ansi", String.valueOf(false));
    }

    private void createCommands (String[] args) {
        new CommandLine(new FoundationCLI())
                .addSubcommand(new CreateSimpleCommand())
                .addSubcommand(new CreateCommandGroup())
                .addSubcommand(new CreateSubCommand())
                .addSubcommand(new CreateSimpleEnchantment())
                .addSubcommand(new CreateSettings())
                .addSubcommand(new CreateRunnable())
                .addSubcommand(new CreateEventListener())
                .addSubcommand(new CreateTool())
                .addSubcommand(new CreateRocket())
                .addSubcommand(new CreateBlockTool())
                .addSubcommand(new CreateSimpleConversation())
                .addSubcommand(new CreateSimplePrompt())
                .addSubcommand(new CreateMenu())
                .addSubcommand(new CreateMenuPagged())
                .addSubcommand(new CreateMenuTools())
                .addSubcommand(new CreateMenuQuantifiable())
                .addSubcommand(new CreateClass())
                .addSubcommand(new CreateQuest())
                .addSubcommand(new CreateRank())
                .addSubcommand(new CreateBoss())
                .addSubcommand(new CreateBossSkill())
                .addSubcommand(new CreateNPC())
                .addSubcommand(new CreatePlayerCache())
                .addSubcommand(new CreateFlatDatabase())
                .execute(args);
    }
}
