package client.commands.available.commands;

import client.commands.Command;
import server.PersonCollection;

/**
 * help : print help for available commands
 */
public class Help extends Command {
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Проблема с аргументом, обратитесь к команде help");
        } else {
            PersonCollection.help();
        }
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }
}
