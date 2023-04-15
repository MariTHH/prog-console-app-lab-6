package commands.available.commands;

import collection.PersonCollection;
import commands.Command;

/**
 * info :
 * output to the standard output stream information about the collection
 * (type, initialization date, number of items, etc.)
 */
public class Info extends Command {
    private final PersonCollection personCollection;

    public Info (PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            personCollection.info();
        }
    }
}
