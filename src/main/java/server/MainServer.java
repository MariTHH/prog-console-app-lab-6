

package server;

import client.RequestManager;
import client.commands.CommandManager;
import common.Configuration;
import common.DataManager;
import common.data.Person;
import common.network.CommandResult;
import common.network.Request;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;

import static server.Parser.convertToJavaObject;

public class MainServer {
    private static int port = Configuration.PORT;

    public static void main(String[] args) throws JAXBException {
        if (args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception exception) {
                System.out.println("Не получается спарсить порт. Используется " + port);
            }
        }
        DataManager dataManager;
        //PersonCollection personCollection = new PersonCollection();
        CommandManager commandManager = new CommandManager(new RequestManager());
        //personCollection.loadCollection();
        String a = commandManager.getFilelink();
        //personCollection.setCollection(convertToJavaObject(new File(CommandManager.getFilelink())).getCollection());
        //personCollection.getCollection();
        PersonCollection collection = new PersonCollection();
        //collection.loadCollection();
        dataManager = collection;

        ServerSocketChannel serverSocketChannel;

        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);

            System.out.println("Сервер запущен. Порт: " + port);
        } catch (IOException exception) {
            System.out.println("Ошибка запуска сервера!");
            System.out.println(exception.getMessage());
            return;
        }


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Выход");
            save(dataManager);
        }));

        Service service = new Service(dataManager);

        AtomicBoolean exit = new AtomicBoolean(false);
        getUserInputHandler(dataManager, exit).start();

        while (!exit.get()) {
            try (SocketChannel socketChannel = serverSocketChannel.accept()) {
                if (socketChannel == null) continue;

                ObjectInputStream objectInputStream1 = new ObjectInputStream(socketChannel.socket().getInputStream());
                ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(socketChannel.socket().getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socketChannel.socket().getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socketChannel.socket().getOutputStream());

                Request<?> request = (Request<?>) objectInputStream1.readObject();
                Request<?> request1 = (Request<?>) objectInputStream.readObject();

                System.out.println(socketChannel.getRemoteAddress() + ": " + request.command);
                CommandResult result = service.executeCommand(request);
                PersonCollection result1 = request1.personCollection;
                //ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(socketChannel.socket().getOutputStream());
                objectOutputStream1.writeObject(result1);

                if (result.status)
                    System.out.println("Команда выполнена успешно");
                else
                    System.out.println("Команда выполнена неуспешно");


                objectOutputStream.writeObject(result);

            } catch (IOException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }

        }
    }

    private static void save(DataManager dataManager) {
        dataManager.save();
    }

    private static Thread getUserInputHandler(DataManager dataManager, AtomicBoolean exit) {
        return new Thread(() -> {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                if (scanner.hasNextLine()) {
                    String serverCommand = scanner.nextLine();

                    switch (serverCommand) {
                        case "save":
                            save(dataManager);
                            break;
                        case "exit":
                            exit.set(true);
                            return;
                        default:
                            System.out.println("Такой команды нет.");
                            break;
                    }
                } else {
                    exit.set(true);
                    return;
                }
            }
        });
    }
}
