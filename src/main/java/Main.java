import client.Console;
import collection.PersonCollection;
import commands.CommandManager;

import java.io.*;
import java.util.*;

import javax.xml.bind.JAXBException;

import static collection.Parser.convertToJavaObject;

/**
 * Main class starts the application
 */
public class Main {
    public static void main(String[] args) throws IllegalArgumentException {

        try {
            PersonCollection collection = new PersonCollection();
            Console console = new Console();

            while (true) {
                try {
                    if (args.length > 0) {
                        String link = args[0];
                        File f = new File(link);
                        if (f.exists() && !f.isDirectory()) {
                            collection.setCollection(convertToJavaObject(f).getCollection());
                            CommandManager commandManager = new CommandManager(collection);
                            commandManager.setFilelink(link);
                            while (CommandManager.getWork()) {
                                CommandManager.existCommand();
                            }
                        } else {
                            console.fileRead();
                        }
                    } else {
                        console.fileRead();
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    console.fileRead();
                }
            }
        } catch (JAXBException | NoSuchElementException e) {
            System.out.println(e.getMessage());
            System.out.println("Приложение не может запуститься");
        }
    }
}

