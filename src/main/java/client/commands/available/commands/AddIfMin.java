package client.commands.available.commands;

import client.ClientManager;
import client.MainClient;
import client.RequestManager;
import client.commands.Command;
import common.data.Person;
import common.network.CommandResult;
import common.network.Request;
import server.PersonCollection;

import javax.xml.bind.JAXBException;
import java.util.Scanner;
import java.util.TreeSet;


/**
 * add_if_min {element} :
 */
public class AddIfMin extends Command {
//

    public AddIfMin(RequestManager requestManager) {
        super(requestManager);
    }

    public String argument;

    /**
     * dd a new element to the collection if its value is less than the smallest element of that collection
     *
     * @param args
     */
    @Override
    public void execute(String[] args) {
        try {
            //if (ExecuteScript.getFlag()) {
            //if (PersonCollection.addIfMaxForScript(args[1])) {
            //ExecuteScript.getPersonList().set(6, args[1]);
            //PersonCollection.addPerson(ClientManager.createPersonFromScript(ExecuteScript.getPersonList()));
            //}
            //}
            if (args.length > 2) {
                System.out.println("Вы неправильно ввели команду");
            } else {
                //personCollection.loadCollection();
                //Request<PersonCollection> = new Request<>(getName(), null, PersonCollection)
                Integer height = Integer.valueOf(args[1]);
                Request<Integer> request = new Request<>(null, height, null);
                CommandResult result = requestManager.sendRequest(request);
                if (!result.status) {
                    Scanner sc = new Scanner(System.in);
                    Person newPerson = ClientManager.getNewPerson(sc);
                    Request<Person> request1 = new Request<>(getName(), newPerson, null);
                    CommandResult result1 = requestManager.sendRequest(request1);

                    if (result1.status) {
                        System.out.println((result.message));
                        System.out.println("Ваш персонаж теперь в коллекции");
                    } else
                        System.out.println("Ошибка");
                } else {
                    System.out.println("Ваш персонаж не самый низкий!!");
                }

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Недостаточно аргументов, обратитесь к команде help");
        }
    }

    @Override
    public String getName() {
        return "add_if_min";
    }

    @Override
    public String getDescription() {
        return "add_if_min: добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}

