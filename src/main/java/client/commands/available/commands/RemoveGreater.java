package client.commands.available.commands;

import client.commands.Command;
import server.PersonCollection;


/**
 * Command remove_greater {element} : remove all elements from the collection that exceed the specified
 */
public class RemoveGreater extends Command {
    private final PersonCollection personCollection;

    public RemoveGreater(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            personCollection.removeGreater(args[1]);
        }
    }
}
