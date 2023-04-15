package commands.available.commands;

import client.ClientManager;
import collection.PersonCollection;
import commands.Command;


/**
 * add_if_min {element} :
 */
public class AddIfMin extends Command {
    private final PersonCollection personCollection;

    public AddIfMin(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    /**
     * dd a new element to the collection if its value is less than the smallest element of that collection
     *
     * @param args
     */
    @Override
    public void execute(String[] args) {
        try {
            if (ExecuteScript.getFlag()) {
                if (personCollection.addIfMinForScript(args[1])) {
                    ExecuteScript.getPersonList().set(6, args[1]);
                    personCollection.addPerson(ClientManager.createPersonFromScript(ExecuteScript.getPersonList()));
                }
            } else if (args.length > 2) {
                System.out.println("Вы неправильно ввели команду");
            } else {
                personCollection.addIfMin(args[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Недостаточно аргументов, обратитесь к команде help");
        }
    }
}

