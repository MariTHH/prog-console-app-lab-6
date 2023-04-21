package client;

import client.commands.CommandManager;
import common.Configuration;
import server.PersonCollection;

import javax.xml.bind.JAXBException;
import java.util.Scanner;

public class MainClient {
    private static int port = Configuration.PORT;

    public static void main(String[] args) throws JAXBException {
        if (args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception exception) {
                System.out.println("Не получается спарсить порт.");
            }
        }
        RequestManager requestManager = new RequestManager(port);
        Scanner scanner = new Scanner(System.in);
        CommandManager commandManager =new CommandManager(requestManager);
        System.out.println("Клиент запущен! Порт: " + port);
        String input;
        do {
            System.out.println("Введите команду: ");
            if (!scanner.hasNextLine()) return;
            input = scanner.nextLine();
            try {
                commandManager.existCommand(input);
            } catch (Exception e) {
                System.out.println("Ошибка");
            }
        } while (!input.equals("exit"));
    }
}
