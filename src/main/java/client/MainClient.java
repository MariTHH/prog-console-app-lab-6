package client;

import client.commands.CommandManager;
import common.Configuration;
import common.data.Person;
import common.network.CommandResult;
import common.network.Request;
import server.PersonCollection;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import static server.Parser.convertToJavaObject;

public class MainClient {
    private static int port = Configuration.PORT;
    //public static PersonCollection collection;

    public static void main(String[] args) throws IOException, JAXBException, ClassNotFoundException {
        if (args.length == 2) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception exception) {
                System.out.println("Не получается спарсить порт.");
            }
        }

        RequestManager requestManager = new RequestManager(port);
        Scanner scanner = new Scanner(System.in);
        CommandManager commandManager = new CommandManager(requestManager);
        //result.loadCollection();
        System.out.println("Клиент запущен! Порт: " + port);
        PersonCollection collection = new PersonCollection();
        if (args.length == 2) {
            collection.setCollection(convertToJavaObject(new File(args[1])).getCollection());
            Request<PersonCollection> request = new Request<>(null, collection, collection);
            PersonCollection result = requestManager.sendCollection(request);
            result.getCollection();
        }
        /**
        String link = args[1];
        File f = new File(link);

      //  PersonCollection personCollection = new PersonCollection();
        if (f.exists() && !f.isDirectory()) {
            //personCollection.setCollection(convertToJavaObject(f).getCollection());
            commandManager.setFilelink(link);

            //Configuration configuration = new Configuration();
            //configuration.setFilelink(link);
//                CommandManager.getFilelink();
        }*/
        String input;
        do {
            System.out.println("Введите команду: ");
            if (!scanner.hasNextLine()) return;
            input = scanner.nextLine();
            try {
                commandManager.existCommand(input);
            } catch (Exception e) {
                System.out.println("Ошибка");
                e.printStackTrace();
            }
        } while (!input.equals("exit"));
    }
}
