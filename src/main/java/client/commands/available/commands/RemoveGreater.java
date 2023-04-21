package client.commands.available.commands;

import client.RequestManager;
import client.commands.Command;
import common.network.CommandResult;
import common.network.Request;
import server.PersonCollection;

import javax.xml.bind.JAXBException;


/**
 * Command remove_greater {element} : remove all elements from the collection that exceed the specified
 */
public class RemoveGreater extends Command {

    public RemoveGreater(RequestManager requestManager) {
        super(requestManager);
    }

    @Override
    public void execute(String[] args) throws JAXBException {
        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            PersonCollection personCollection = new PersonCollection();
            personCollection.loadCollection();
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
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return "remove_greater: удалить из коллекции все элементы, превышающие заданный";
    }
}
