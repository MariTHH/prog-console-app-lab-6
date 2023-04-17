package client;

import client.commands.CommandManager;
import server.PersonCollection;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Scanner;

import static server.Parser.convertToJavaObject;

/**
 * Reads the file for the main
 */
public class Console {
    PersonCollection collection = new PersonCollection();

    public void fileRead() throws JAXBException {
        while (true) {
            try {
                System.out.println("Введите название файла еще раз");
                Scanner scanner = new Scanner(System.in);
                String Path = scanner.nextLine();
                File file = new File(String.valueOf(Path));
                collection.setCollection(convertToJavaObject(file).getCollection());
                CommandManager commandManager = new CommandManager(collection);
                commandManager.setFilelink(Path);
                while (CommandManager.getWork()) {
                    CommandManager.existCommand();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Файл не найден");
            }
        }
    }
}

