package commands;

import collection.PersonCollection;
import commands.available.commands.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The class is responsible for checking for the correctness of commands and running them
 */
public class CommandManager {
    private final PersonCollection personCollection;
    private static boolean isWorking = true;
    private static HashMap<String, Command> commandMap = new HashMap();
    private static String filelink;

    /**
     * creates a commandMap with commands
     *
     * @param personCollection
     */
    public CommandManager(PersonCollection personCollection) {
        this.personCollection = personCollection;
        commandMap = new HashMap<>();
        commandMap.put("add", new Add(personCollection));
        commandMap.put("add_if_max", new AddIfMax(personCollection));
        commandMap.put("add_if_min", new AddIfMin(personCollection));
        commandMap.put("clear", new Clear(personCollection));
        commandMap.put("remove_by_id", new RemoveById(personCollection));
        commandMap.put("show", new Show(personCollection));
        commandMap.put("remove_greater", new RemoveGreater(personCollection));
        commandMap.put("count_greater_than_eye_color", new CountGreaterThanEyeColor(personCollection));
        commandMap.put("update", new Update(personCollection));
        commandMap.put("filter_greater_than_location", new FilterGreaterThanLocation(personCollection));
        commandMap.put("print_unique_location", new PrintUniqueLocation(personCollection));
        commandMap.put("info", new Info(personCollection));
        commandMap.put("help", new Help());
        commandMap.put("save", new Save(personCollection));
        commandMap.put("exit", new Exit());
        commandMap.put("execute_script", new ExecuteScript(personCollection));
    }

    public PersonCollection getPersonCollection() {
        return this.personCollection;
    }

    /**
     * checks for the correctness of the command and starts
     */
    public static void existCommand() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Введите команду: ");
            String command = sc.nextLine().trim();

            String[] commandArg = command.split(" ");
            String argument;

            if (commandArg.length == 1)
                argument = null;
            else if (commandArg.length == 2)
                argument = commandArg[1];
            else {
                System.out.println("Проблема с аргументом, обратитесь к команде help");
                return;
            }

            if (commandMap.containsKey(commandArg[0])) {
                commandMap.get(commandArg[0]).setArgument(argument);
                commandMap.get(commandArg[0]).execute(commandArg);
            } else {
                System.out.println("Команды " + commandArg[0] + " не существует");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Команда введена неверно");
            isWorking = false;
            System.exit(0);
        } catch (JAXBException | IOException e) {
            System.out.println("Файл не найден");
        }

    }

    public static boolean getWork() {
        return isWorking;
    }

    public static HashMap<String, Command> getCommandMap() {
        return commandMap;
    }

    public static String getFilelink() {
        return filelink;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }


}



