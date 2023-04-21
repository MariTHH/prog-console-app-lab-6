package client.commands.available.commands;

import client.RequestManager;
import client.commands.Command;
import common.data.Person;
import common.network.CommandResult;
import common.network.Request;
import server.PersonCollection;

import javax.xml.bind.JAXBException;
import java.util.Scanner;

/**
 * Command remove_by_id id : remove an item from the collection by its id
 */
public class RemoveById extends Command {

    public RemoveById(RequestManager requestManager) {
        super(requestManager);
    }

    /**public static void remove_by_idForScript() {
        try {
            System.out.println("Введите Id персонажа, которого хотите удалить");
            Scanner sc = new Scanner(System.in);
            String a = sc.nextLine();
            int ID = Integer.parseInt(a);
            if (personCollection.existID(ID)) {
                personCollection.removePerson(ID);
                System.out.println("Персонаж удален");
            } else {
                System.out.println("Этого персонажа не существует");
            }
        } catch (NumberFormatException e) {
            System.out.println("Вы неправильно ввели ID");
        }
    }*/

    @Override
    public void execute(String[] args) throws JAXBException {
        if (ExecuteScript.getFlag()) {
            //RemoveById.remove_by_idForScript();
        } else if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            Request<String> request = new Request<>(getName(), args[1]);
            CommandResult result = requestManager.sendRequest(request);
            if (result.status) {
                System.out.println((result.message));
            } else
                System.out.println("Ошибка");
        }
    }
    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "remove_by_id: удалить элемент из коллекции по его id";
    }
}

