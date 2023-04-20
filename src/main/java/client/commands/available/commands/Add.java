package client.commands.available.commands;

import client.*;
import client.commands.Command;
import common.data.Person;
import common.network.*;

import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Command add {element}
 */
public class Add extends Command {


    public Add(RequestManager requestManager) {
        super(requestManager);


    }


    /**
     * add a new element to the collection
     *
     * @param args
     */
    @Override
    public void execute(String[] args) throws FileNotFoundException {
        //if (ExecuteScript.getFlag()) {
        //personCollection.addPerson(ClientManager.createPersonFromScript(ExecuteScript.getPersonList()));
//        }
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            Scanner sc = new Scanner(System.in);
            Person newPerson = ClientManager.getNewPerson(sc);
            Request<Person> request = new Request<>(getName(), newPerson);
            CommandResult result = requestManager.sendRequest(request);
            if (result.status) {
                System.out.println((result.message));
                System.out.println("Ваш персонаж теперь в коллекции");
            } else
                System.out.println("Ошибка");
        }
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add : добавить новый элемент в коллекцию";
    }


}
