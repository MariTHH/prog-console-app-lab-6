package commands.available.commands;

import client.ClientManager;
import collection.PersonCollection;
import commands.*;

import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Command add {element}
 */
public class Add extends Command {
    private final PersonCollection personCollection;

    public Add(PersonCollection personCollection) {
        this.personCollection = personCollection;

    }


    /**
     * add a new element to the collection
     *
     * @param args
     */
    @Override
    public void execute(String[] args) throws FileNotFoundException {
        if (ExecuteScript.getFlag()) {
            personCollection.addPerson(ClientManager.createPersonFromScript(ExecuteScript.getPersonList()));
        } else if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            Scanner sc = new Scanner(System.in);
            personCollection.addPerson(ClientManager.getNewPerson(sc));
            System.out.println("Ваш персонаж теперь в коллекции");
        }
    }


}
