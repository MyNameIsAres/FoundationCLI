package org.ares.foundation.cli;

import org.ares.foundation.cli.impl.command.CreateSimpleCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command
public class FoundationCLI implements Runnable{

    public static void main(String[] args) {
        new CommandLine(new FoundationCLI())
                .addSubcommand(new CreateSimpleCommand())
                .execute(args);
    }

    @Override
    public void run() {

    }
}
