package client.commands.available.commands;

import client.ClientManager;
import client.commands.Command;
import server.PersonCollection;

import java.util.Scanner;

/**
 * Command update id {element} : update the value of the collection item whose id is equal to the given one
 */
public class Update extends Command {
    private final PersonCollection personCollection;

    public Update(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute(String[] args) {
        if (ExecuteScript.getFlag()) {
            updateForScript();
        } else if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            Scanner sc = new Scanner(System.in);
            update(args[1], sc);
        }
    }

    public void update(String arg, Scanner sc) {
        try {
            int ID = Integer.parseInt(arg);
            if (personCollection.existID(ID)) {
                personCollection.updateElement(ClientManager.getNewPerson(sc), ID);
                System.out.println("Персонаж обновлен");
            } else {
                System.out.println("Человека с таким ID не существует");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID введен неверно");
        }
    }

    public void updateForScript() {
        try {
            System.out.println("Введите ID для команды update");
            Scanner scanner = new Scanner(System.in);
            int line = Integer.parseInt(scanner.nextLine().trim());
            if (personCollection.existID(line)) {
                System.out.println("Персонаж обновлен");
                personCollection.updateElement(ClientManager.createPersonFromScript(ExecuteScript.getPersonList()), line);
            } else {
                System.out.println("Человека с таким ID не существует");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("jd");
        }
    }


    @Override
    public String getName() {
        return "update";

    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

}


