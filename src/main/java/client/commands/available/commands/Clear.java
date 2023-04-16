package client.commands.available.commands;

import client.commands.Command;
import server.PersonCollection;


/**
 * Command clear : clear the collection
 */
public class Clear extends Command {
    private final PersonCollection personCollection;

    public Clear(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            clear();
        }
    }

    public void clear() {
        personCollection.clearCollection();
        System.out.println("Коллекция очищена");
    }
}
