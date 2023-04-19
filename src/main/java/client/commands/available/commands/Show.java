package client.commands.available.commands;

import client.commands.Command;
import common.data.Person;
import common.network.CommandResult;
import common.network.Request;
import server.PersonCollection;

/**
 * Command show. Output to the standard output stream all elements of the collection in string representation
 */
public class Show extends Command {
    private PersonCollection personCollection;

    public Show(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
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

}
