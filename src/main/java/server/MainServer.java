package server;

import common.Configuration;
import common.DataManager;
import common.network.CommandResult;
import common.network.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainServer {
    private static int port = Configuration.PORT;

    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception exception) {
                System.out.println("Не получается спарсить порт. Используется " + port);
            }
        }
        DataManager dataManager;
        try {
            dataManager = new PersonCollection(new Parser());
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }

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
        }));

        Service service = new Service(dataManager);

        AtomicBoolean exit = new AtomicBoolean(false);

        while (!exit.get()) {
            try (SocketChannel socketChannel = serverSocketChannel.accept()) {
                if (socketChannel == null) continue;

                ObjectInputStream objectInputStream = new ObjectInputStream(socketChannel.socket().getInputStream());
                Request<?> request = (Request<?>) objectInputStream.readObject();
                System.out.println(socketChannel.getRemoteAddress() + ": " + request.command);

                CommandResult result = service.executeCommand(request);
                if (result.status)
                    System.out.println("Команда выполнена успешно");
                else
                    System.out.println("Команда выполнена неуспешно");

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socketChannel.socket().getOutputStream());
                objectOutputStream.writeObject(result);
            } catch (IOException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        }
    }
   
}
