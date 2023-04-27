package client.commands.available.commands;

import client.ClientManager;
import client.RequestManager;
import client.commands.Command;
import common.data.Person;
import common.network.CommandResult;
import common.network.Request;
import server.PersonCollection;

import javax.xml.bind.JAXBException;
import java.util.Scanner;

/**
 * Command update id {element} : update the value of the collection item whose id is equal to the given one
 */
public class Update extends Command {
    public Update(RequestManager requestManager) {
        super(requestManager);
    }


    @Override
    public void execute(String[] args) throws JAXBException {
        //if (ExecuteScript.getFlag()) {
        //updateForScript();
//        } else
        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            PersonCollection personCollection = new PersonCollection();
           //personCollection.loadCollection();
            //System.out.println(personCollection.);
            for (Person person : personCollection.getCollection()) {
                if (person.getId() == Integer.parseInt(args[1])) {
                    Person person1 = ClientManager.getNewPerson(new Scanner(System.in));
                    Request<Person> request = new Request<>(getName(), person1, null);
                    CommandResult result = requestManager.sendRequest(request);
                    if (result.status) {
                        System.out.println((result.message));
                    } else
                        System.out.println("Ошибка");
                }

            }
        }
        }

        /**
         * public void updateForScript() {
         * try {
         * System.out.println("Введите ID для команды update");
         * Scanner scanner = new Scanner(System.in);
         * int line = Integer.parseInt(scanner.nextLine().trim());
         * if (personCollection.existID(line)) {
         * System.out.println("Персонаж обновлен");
         * personCollection.updateElement(ClientManager.createPersonFromScript(ExecuteScript.getPersonList()), line);
         * } else {
         * System.out.println("Человека с таким ID не существует");
         * }
         * } catch (IllegalArgumentException e) {
         * System.out.println("jd");
         * }
         * }
         */

        @Override
        public String getName () {
            return "update";

        }

        @Override
        public String getDescription () {
            return "update: обновить значение элемента коллекции, id которого равен заданному";
        }

    }


