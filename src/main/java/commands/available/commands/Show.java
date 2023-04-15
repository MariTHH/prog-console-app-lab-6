package commands.available.commands;

import collection.PersonCollection;
import commands.Command;

/**
 * Command show. Output to the standard output stream all elements of the collection in string representation
 */
public class Show extends Command {
    private PersonCollection personCollection;

    public Show(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            personCollection.information();
        }
    }

}
