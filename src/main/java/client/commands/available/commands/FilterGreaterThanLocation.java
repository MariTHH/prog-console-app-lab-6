package client.commands.available.commands;

import client.RequestManager;
import client.commands.Command;
import common.network.CommandResult;
import common.network.Request;
import server.PersonCollection;

import javax.xml.bind.JAXBException;

/**
 * filter_greater_than_location location :
 * display elements whose location field value is greater than the given one
 */
public class FilterGreaterThanLocation extends Command {

    public FilterGreaterThanLocation(RequestManager requestManager) {
        super(requestManager);
    }

    @Override
    public void execute(String[] args) throws JAXBException {
        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            //personCollection.filterGreater(args[1]);
            PersonCollection personCollection = new PersonCollection();
            //personCollection.loadCollection();
            Request<String> request = new Request<>(getName(), args[1],null);
            CommandResult result = requestManager.sendRequest(request);
            if (result.status) {
                System.out.println((result.message));
            } else
                System.out.println("Ошибка");
        }
    }

    @Override
    public String getName() {
        return "filter_greater_than_location";
    }

    @Override
    public String getDescription() {
        return "filter_greater_than_location: вывести элементы, значение поля location которых больше заданного";
    }
}
