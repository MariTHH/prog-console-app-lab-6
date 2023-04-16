package client.commands.available.commands;

import client.commands.Command;
import client.commands.CommandManager;
import server.PersonCollection;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * save : save the collection to a file
 */
public class Save extends Command {
    private final PersonCollection collection;

    public Save(PersonCollection collection) {
        this.collection = collection;
    }


    @Override
    public void execute(String[] args) throws JAXBException, IOException {
        try {
            if (args.length != 2) {
                System.out.println("Вы неправильно ввели команду");
            } else {
                collection.save(args[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            collection.save(CommandManager.getFilelink());
        }
    }

}


