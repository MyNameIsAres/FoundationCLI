package org.ares.foundation.cli;

import org.ares.foundation.cli.impl.CreateSimpleEnchantment;
import org.ares.foundation.cli.impl.command.CreateCommandGroup;
import org.ares.foundation.cli.impl.command.CreateSimpleCommand;
import org.ares.foundation.cli.impl.command.CreateSubCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command
public class FoundationCLI implements Runnable{

    public static void main(String[] args) {
        disableAnsi();

        new CommandLine(new FoundationCLI())
                .addSubcommand(new CreateSimpleCommand())
                .addSubcommand(new CreateCommandGroup())
                .addSubcommand(new CreateSubCommand())
                .addSubcommand(new CreateSimpleEnchantment())
                .execute(args);
    }

    @Override
    public void run() {
        this.welcomeMessage();
    }

    private void welcomeMessage() {
        System.out.println("==============================");
        System.out.println("Welcome to FoundationCLI!");
        System.out.println();
        System.out.println("Please check the Wiki below for a complete list of all possible commands.");
        System.out.println("Join the community on https://www.skool.com/project-orion");
        System.out.println();
        System.out.println("This project is still in development. Please report an issue <link>");
        System.out.println("The code quality of this project is to be improved in the coming weeks.");
        System.out.println("==============================");
    }

    private static void disableAnsi() {
        System.setProperty("picocli.ansi", String.valueOf(false));
    }
}
