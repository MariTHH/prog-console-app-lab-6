package server;

import client.ClientManager;
import client.RequestManager;
import client.commands.Command;
import client.commands.CommandManager;
import common.DataManager;
import common.data.Person;
import common.network.CommandResult;
import common.network.Request;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

import static server.Parser.convertToJavaObject;


/**
 * A class that implements collection related methods
 */
@XmlRootElement(
        name = "persons"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonCollection extends DataManager {
    @XmlElement(name = "Person")
    private String filename;
    private Parser parser;
    private TreeSet<Person> treeSet = new TreeSet<>();
    private static Date creationDate = new Date();
    //private final Comparator<Person> sortByName = Comparator.comparing(Person::getName);

    public PersonCollection(Parser parser) throws JAXBException {
        //this.treeSet = treeSet;
        //this.filename = filename;
        this.parser = parser;
        loadCollection();
    }

    public PersonCollection() {

    }

    public void loadCollection() throws JAXBException {
        treeSet = convertToJavaObject(new File("s")).getCollection();
    }

    /**
     * adds Person
     *
     * @param person
     */
    public void addPerson(Person person) {
        treeSet.add(person);
    }

    /**
     * @return
     */
    public TreeSet<Person> getCollection() {
        return treeSet;
    }

    /**
     * displays information about the character with all fields
     *
     * @param person
     * @return
     */
    public void personInfo(Person person) {
        System.out.println("ID: " + person.getId());
        System.out.println("Имя персонажа: " + person.getName());
        System.out.println("Координаты: X=" + person.getCoordinates().getX() + ", Y=" + person.getCoordinates().getY());
        System.out.println("Время создания: " + person.getCreationDate());
        System.out.println("Рост: " + person.getHeight());
        System.out.println("Цвет глаз: " + person.getEyeColor());
        System.out.println("Цвет волос: " + person.getHairColor());
        System.out.println("Страна: " + person.getNationality());
        System.out.println("Локация: " + "X: " + person.getLocation().getX() + " Y: " + person.getLocation().getY() + " Название: " + person.getLocation().getLocationName());
    }

    public CommandResult add(Request<?> request) {
        try {
            Person person = (Person) request.type;
            //person.setId(generateNextId());
            treeSet.add(person);
            return new CommandResult(true, "Новый элемент успешно добавлен");
        } catch (Exception exception) {
            return new CommandResult(false, "Передан аргумент другого типа");
        }
    }


    /**
     * displays information about each person
     */
    public String information() {
        if (treeSet.isEmpty()) {
            return "В коллекции ничего нет";
        }
        for (Person person : treeSet) {
            personInfo(person);
        }
        return "good";
    }

    public CommandResult show(Request<?> request) {
        return new CommandResult(true, information());
    }

    /**
     * method which compares the characters' height
     * @param height_int
     * @return true or false
     */
    public boolean toHeight(int height_int) {
        boolean flag = true;
        for (Person person : treeSet) {
            if (height_int > person.getHeight()) {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public CommandResult addIfMax(Request<?> request) {
        Person person = (Person) request.type;
        addPerson(person);
        //person.setHeight(height_int);
        return new CommandResult(true, "Новый элемент успешно добавлен");
    }

    public CommandResult addIfMin(Request<?> request) {
        Person person = (Person) request.type;
        addPerson(person);
        //person.setHeight(height_int);
        return new CommandResult(true, "Новый элемент успешно добавлен");
    }


    /**
     * clears the collection
     */
    public CommandResult clear(Request<?> request) {
        treeSet.clear();
        return new CommandResult(true, "Элементы удалены");
    }

    /**
     * @param ID could be int
     * @return
     */
    public boolean existID(int ID) {
        for (Person person : treeSet) {
            if (person.getId() == ID) {
                return true;
            }
        }
        return false;
    }

    /**
     * removes person
     *
     * @param ID
     */
    public void removePerson(int ID) {
        for (Person person : treeSet) {
            if (existID(ID)) {
                treeSet.remove(person);
                break;
            }
        }
    }

    /**
     * updates data of person, ID stays the same
     *
     * @param newPerson
     * @param ID
     */
    public void updateElement(Person newPerson, int ID) {
        for (Person person : treeSet) {
            if (person.getId() == ID) {
                person.setName(newPerson.getName());
                person.setCoordinates(newPerson.getCoordinates());
                person.setCreationDate(newPerson.getCreationDate());
                person.setHeight(newPerson.getHeight());
                person.setEyeColor(newPerson.getEyeColor());
                person.setHairColor(newPerson.getHairColor());
                person.setNationality(newPerson.getNationality());
                person.setLocation(newPerson.getLocation());
            }
        }
    }

    /**
     * removes the highest person
     *
     * @param sc
     */
    public void removeGreater(String sc) {
        String height_s = sc.trim();
        int height = Integer.parseInt(height_s);

        treeSet.removeIf(person -> person.getHeight() > height);

    }

    /**
     * counter of persons whose color code is greater
     *
     * @param code
     */
    public void countEyeColor(int code) {
        int count = 0;
        for (Person person : treeSet) {
            if (person.getEyeColor().getCode() == code) {
                count += 1;
            }
        }
        System.out.println(count);
    }

    /**
     * filter of persons whose coordinate is greater
     *
     * @param xString
     */
    public void filterGreater(String xString) {
        try {
            double x = Double.parseDouble(xString);

            for (Person person : treeSet) {
                if (person.getLocation().getX() > x) {
                    System.out.println(person.getName() + " : " + person.getLocation().getX());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Вы неправильно ввели данные");
        }
    }

    private static final ArrayList<Double> uniq = new ArrayList<>();

    /**
     * print a not repeated location
     */
    public void printUniqueLocation() {
        for (Person person : treeSet) {
            double X = person.getLocation().getX();
            if (!uniq.contains(X)) {
                uniq.add(X);
            } else {
                uniq.remove(X);
            }
        }
        System.out.println(uniq);
    }

    /**
     * print info about collection
     */
    public CommandResult info(Request<?> request) {
        System.out.println(treeSet.getClass().getName() + " " + PersonCollection.creationDate + " " + treeSet.size());
        return new CommandResult(true, "Выведена имнформация о коллекции");
    }

    /**
     * print information about available commands
     */
    public static void help() {
        for (Map.Entry<String, Command> e : CommandManager.getCommandMap().entrySet()) {
            Command command = e.getValue();
            System.out.println(command.getName() + " : " + command.getDescription());
        }
    }
*/
  public CommandResult help(Request<?> request) {
      CommandManager commandManager = new CommandManager(new RequestManager(), new PersonCollection());
      commandManager.getCommandMap().forEach((description,command) -> System.out.println(command.getDescription()));
        return new CommandResult(true,"Выведена информация о командах");
    }

    /**
     * set collection
     *
     * @param treeSet
     */
    public void setCollection(TreeSet<Person> treeSet) {

        for (Person person : treeSet) {
            person.setName(person.getName());
            person.setNationality(person.getNationality());
            person.setCoordinates(person.getCoordinates());
            person.setEyeColor(person.getEyeColor());
            person.setHairColor(person.getHairColor());
            person.setLocation(person.getLocation());
            person.setHeight(person.getHeight());
        }

        this.treeSet = treeSet;
    }

    /**
     * saves collection to file XML
     *
     * @throws JAXBException
     * @throws IOException
     */
    public void save(String file) throws JAXBException, IOException {
        try {
            String sc = file.trim();
            Parser.convertToXML(this, sc);
        } catch (FileNotFoundException e) {
            System.out.println("Файл для сохранения не найден");
        } catch (NullPointerException e) {
            System.out.println("Сохранит в текущий файл");
            Parser.convertToXML(this, file);
        }
    }

    /**
     * adds a person if he is higher than the other for script
     *
     * @param sc
     */
    public boolean addIfMaxForScript(String sc) {
        String height_s = sc.trim();
        int height_int = Integer.parseInt(height_s);
        boolean flag = false;
        for (Person person1 : treeSet) {
            flag = height_int > person1.getHeight();

        }
        if (flag) {
            System.out.println("Самый высокий персонаж добавлен");
            return true;
        } else {
            System.out.println("Персонаж не выше всех");
            return false;
        }
    }

    /**
     * adds a person if he is lower than the other
     *
     * @param sc
     */
    public boolean addIfMinForScript(String sc) {
        String height_s = sc.trim();
        int height_int = Integer.parseInt(height_s);
        boolean flag = false;
        for (Person person1 : treeSet) {
            flag = height_int < person1.getHeight();

        }
        if (flag) {
            System.out.println("Самый низкий персонаж добавлен");
            return true;
        } else {
            System.out.println("Персонаж не ниже всех");
            return false;
        }
    }

}

