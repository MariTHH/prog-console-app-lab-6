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
 * add_if_max {element}
 */
public class AddIfMax extends Command {

    public AddIfMax(RequestManager requestManager) {
        super(requestManager);
    }

    /**
     * add a new element to a collection if its value exceeds the value of the largest element of that collection
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
                PersonCollection personCollection = new PersonCollection();
                //personCollection.loadCollection();
                if (personCollection.toHeight(Integer.parseInt(args[1]))) {
                    Scanner sc = new Scanner(System.in);
                    Person newPerson = ClientManager.getNewPerson(sc);
                    Request<Person> request = new Request<>(getName(), newPerson);
                    CommandResult result = requestManager.sendRequest(request);

                    if (result.status) {
                        System.out.println((result.message));
                        System.out.println("Ваш персонаж теперь в коллекции");
                    } else
                        System.out.println("ОШИБКА!");
                } else {
                    System.out.println("Ваш персонаж не самый высокий!!");
                }

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Недостаточно аргументов, обратитесь к команде help");
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescription() {
        return "add_if_max: добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

}