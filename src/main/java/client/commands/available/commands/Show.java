package client.commands.available.commands;

import client.RequestManager;
import client.commands.Command;
import common.data.Person;
import common.network.CommandResult;
import common.network.Request;
import server.PersonCollection;

import javax.xml.bind.JAXBException;
import java.io.EOFException;

/**
 * Command show. Output to the standard output stream all elements of the collection in string representation
 */
public class Show extends Command {

    public Show(RequestManager requestManager) {
        super(requestManager);

    }

    @Override
    public void execute(String[] args) throws JAXBException {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            Request<String> request = new Request<>(getName(), null, null);
            CommandResult result = requestManager.sendRequest(request);
            if (result.status) {
                System.out.println((result.message));
            } else
                System.out.println("Ошибка");
        }
    }


    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

}
