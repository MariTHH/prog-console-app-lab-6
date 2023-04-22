package client.commands.available.commands;

import client.commands.Command;

/**
 * exit : terminate the program (without saving to a file)
 */
public class Exit extends Command {
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            System.out.println("Удачи");

        }
    }
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "exit: завершить программу (без сохранения в файл)";
    }
}
