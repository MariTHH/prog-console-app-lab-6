package client.commands.available.commands;

import client.ClientManager;
import client.RequestManager;
import client.commands.Command;
import common.data.Person;
import common.network.CommandResult;
import common.network.Request;
import server.PersonCollection;

import javax.xml.bind.JAXBException;
import java.util.Scanner;

/**
 * Command update id {element} : update the value of the collection item whose id is equal to the given one
 */
public class Update extends Command {
    public Update(RequestManager requestManager) {
        super(requestManager);
    }


    @Override
    public void execute(String[] args) throws JAXBException {
        int id = 0;
        Person person1;
        if (args.length > 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            if (ExecuteScript.getFlag()) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите ID");
                id = Integer.parseInt(scanner.nextLine());
            } else {
                id = Integer.parseInt(args[1]);
            }
            Request<Integer> request = new Request<>(null, id, null);
            CommandResult result = requestManager.sendRequest(request);
            if (result.status) {
                if (!ExecuteScript.getFlag()) {
                    person1 = ClientManager.getNewPerson(new Scanner(System.in));
                    person1.setId(id);
                } else{
                    person1 = ClientManager.createPersonFromScript(ExecuteScript.getPersonList());
                    person1.setId(id);
                }
                Request<Person> request1 = new Request<>(getName(), person1, null);
                CommandResult result1 = requestManager.sendRequest(request1);
                if (result1.status) {
                    System.out.println((result.message));
                } else
                    System.out.println("Ошибка");
            }
        }
    }


    @Override
    public String getName() {
        return "update";

    }

    @Override
    public String getDescription() {
        return "update: обновить значение элемента коллекции, id которого равен заданному";
    }

}


