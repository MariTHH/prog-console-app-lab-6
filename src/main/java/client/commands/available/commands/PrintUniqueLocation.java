package client.commands.available.commands;

import client.commands.Command;
import server.PersonCollection;

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
    @Override
    public String getName() {
        return "print_unique_location";
    }

    @Override
    public String getDescription() {
        return "print_unique_location: вывести уникальные значения поля location всех элементов в коллекции";
    }
}
