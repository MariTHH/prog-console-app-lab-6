package client.commands.available.commands;

import client.RequestManager;
import client.commands.Command;
import common.data.Person;
import common.network.CommandResult;
import common.network.Request;
import server.PersonCollection;

import javax.xml.bind.JAXBException;


/**
 * Command clear : clear the collection
 */
public class Clear extends Command {

    public Clear(RequestManager requestManager) {
        super(requestManager);
    }

    @Override
    public void execute(String[] args) throws JAXBException {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            PersonCollection personCollection = new PersonCollection();
            //personCollection.loadCollection();
            Request<?> request = new Request<String>(getName(),null,null);
            CommandResult result = requestManager.sendRequest(request);
            if (result.status) {
                System.out.println("Коллекция очищена");
                System.out.println((result.message));
            }
        }
    }
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clear: очистить коллекцию";
    }
}
