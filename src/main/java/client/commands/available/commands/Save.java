package client.commands.available.commands;

import client.RequestManager;
import client.commands.Command;
import client.commands.CommandManager;
import common.network.CommandResult;
import common.network.Request;
import server.PersonCollection;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * save : save the collection to a file
 */
public class Save extends Command {


    public Save(RequestManager requestManager) {
        super(requestManager);
    }


    @Override
    public void execute(String[] args) throws JAXBException, IOException {

        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            Request<String> request = new Request<>(getName(), null);
            CommandResult result = requestManager.sendRequest(request);
            if (result.status) {
                System.out.println((result.message));
            } else
                System.out.println("Ошибка");

        }
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "save: сохранить коллекцию в файл";
    }
}


