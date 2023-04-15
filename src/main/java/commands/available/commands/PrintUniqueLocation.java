package commands.available.commands;

import collection.PersonCollection;
import commands.Command;

/**
 * print_unique_location : print the unique values of the location field of all items in the collection
 */
public class PrintUniqueLocation extends Command {
    private final PersonCollection personCollection;

    public PrintUniqueLocation(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            personCollection.printUniqueLocation();
        }
    }
}
