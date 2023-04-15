package commands.available.commands;

import client.ClientManager;
import collection.PersonCollection;
import commands.Command;


/**
 * add_if_max {element}
 */
public class AddIfMax extends Command {
    private final PersonCollection personCollection;

    public AddIfMax(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    /**
     * add a new element to a collection if its value exceeds the value of the largest element of that collection
     *
     * @param args
     */
    @Override
    public void execute(String[] args) {
        try {
            if (ExecuteScript.getFlag()) {
                if (personCollection.addIfMaxForScript(args[1])) {
                    ExecuteScript.getPersonList().set(6, args[1]);
                    personCollection.addPerson(ClientManager.createPersonFromScript(ExecuteScript.getPersonList()));
                }
            } else if (args.length != 2) {
                System.out.println("Вы неправильно ввели команду");
            } else {
                personCollection.addIfMax(args[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Недостаточно аргументов, обратитесь к команде help");
        }
    }
}