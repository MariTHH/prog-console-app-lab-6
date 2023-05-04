package server;

import client.RequestManager;
import client.commands.CommandManager;
import common.DataManager;
import common.data.Color;
import common.data.Person;
import common.network.CommandResult;
import common.network.Request;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;


/**
 * A class that implements collection related methods
 */
@XmlRootElement(
        name = "persons"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonCollection extends DataManager implements Serializable {
    @XmlElement(name = "Person")
    private String filename;
    private Parser parser;
    private TreeSet<Person> treeSet = new TreeSet<>();
    private static Date creationDate = new Date();
    //private final Comparator<Person> sortByName = Comparator.comparing(Person::getName);

    public PersonCollection(Parser parser) throws JAXBException {
        this.parser = parser;
    }

    public PersonCollection() {

    }

    public void loadCollection(TreeSet<Person> request) throws JAXBException {
        RequestManager requestManager = new RequestManager();
        CommandManager commandManager = new CommandManager(requestManager);
        //String link = CommandManager.getFilelink();
        //treeSet.;
        setCollection(request);
        //treeSet = result;
    }

    /**
     * adds Person
     *
     * @param person
     */
    public void addPerson(Person person) {
        treeSet.add(person);
    }

    public TreeSet<Person> getCollection() {
        return treeSet;
    }

    /**
     * displays information about the character with all fields
     *
     * @param person
     * @return
     */
    public String personInfo(Person person) {
        return ("ID: " + person.getId() +
                "\nИмя персонажа: " + person.getName() +
                "\nКоординаты: X=" + person.getCoordinates().getX() + ", Y=" + person.getCoordinates().getY() +
                "\nВремя создания: " + person.getCreationDate() +
                "\nРост: " + person.getHeight() +
                "\nЦвет глаз: " + person.getEyeColor() +
                "\nЦвет волос: " + person.getHairColor() +
                "\nСтрана: " + person.getNationality() +
                "\nЛокация: " + "X: " + person.getLocation().getX() + " Y: " + person.getLocation().getY() + " Название: " + person.getLocation().getLocationName() + "\n");

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
        StringBuilder info = new StringBuilder();
        for (Person person : this.treeSet) {
            info.append(personInfo(person));

        }
        return info.toString();
        //return "Коллекция выведена";
    }

    public CommandResult show(Request<?> request) {
        return new CommandResult(true, information());
    }

    /**
     * method which compares the characters' height
     *
     * @param
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


    public CommandResult remove_by_id(Request<?> request) {
        String message = null;
        int ID;
        try {
            ID = Integer.parseInt((String) request.type);
            if (treeSet.stream().noneMatch(person -> person.getId() == (ID)))
                return new CommandResult(false, "Персонажа с таким ID не существует");
            treeSet.removeIf(person -> person.getId() == (ID));
            return new CommandResult(true, "Персонаж успешно удален");
        } catch (NumberFormatException e) {
            message = "Вы неправильно ввели ID";
        }
        return new CommandResult(true, message);
    }


    /**
     * removes the highest person
     *
     * @param
     */
    public CommandResult removeGreater(Request<?> request) {
        String message = null;
        try {
            int height = Integer.parseInt((String) request.type);
            if (height > 0) {
                treeSet.removeIf(person -> person.getHeight() > height);
                message = "Удален персонаж выше заданного роста";
            } else {
                message = "Рост не может быть меньше нуля";
            }
        } catch (NumberFormatException e) {
            System.out.println("Рост введен некорректно");
        }
        return new CommandResult(true, message);
    }

    /**
     * filter of persons whose coordinate is greater
     *
     * @param
     */
    public CommandResult filterGreater(Request<?> request) {
        try {
            double x = Double.parseDouble((String) request.type);

            for (Person person : treeSet) {
                if (person.getLocation().getX() > x) {
                    return new CommandResult(true, person.getName() + " : " + person.getLocation().getX());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Вы неправильно ввели данные");
        }
        return new CommandResult(true, "Выведен персонаж с большей координатой локации");
    }

    private static final ArrayList<Double> uniq = new ArrayList<>();

    /**
     * print a not repeated location
     */
    public CommandResult printUniqueLocation(Request<?> request) {
        for (Person person : treeSet) {
            double X = person.getLocation().getX();
            if (!uniq.contains(X)) {
                uniq.add(X);
            } else {
                uniq.remove(X);
            }
            System.out.println("Выведены все уникальные значения");
        }
        return new CommandResult(true, String.valueOf(uniq));
    }

    /**
     * print info about collection
     */
    public CommandResult info(Request<?> request) {
        String inf = treeSet.getClass().getName() + " " + PersonCollection.creationDate + " " + treeSet.size();
        return new CommandResult(true, inf);
    }

    /**
     * print information about available commands
     */
    public CommandResult help(Request<?> request) {
        StringBuilder result = new StringBuilder();
        CommandManager commandManager = new CommandManager(new RequestManager());
        commandManager.getCommandMap().forEach((description, command) -> result.append(command.getDescription()).append("\n"));
        return new CommandResult(true, result.toString());
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


    public CommandResult update(Request<?> request) {
        String message = null;
        try {
            Person person = (Person) request.type;
            if (getById(person.getId()) == null) {
                return new CommandResult(false, "Персонажа с таким ID не существует");
            }
            treeSet.stream()
                    .filter(person1 -> person1.getId() == person.getId())
                    .forEach(person1 -> person1.update(person));
            return new CommandResult(true, "Персонаж успешно обновлен");
        } catch (NumberFormatException e) {
            System.out.println("ID введен неверно");
        }
        return new CommandResult(true, message);
    }

    public Person getById(Integer id) {
        return treeSet.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean countGreater2(int eyeColor_int) {

        boolean flag = false;

        for (Color ourColor : Color.values()) {
            if (ourColor.getCode() == eyeColor_int) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * counter of persons whose color code is greater
     *
     * @param
     */
    public CommandResult countEyeColor(Request<?> request) {
        int count = 0;
        Integer code = Integer.parseInt((String) request.type);
        for (Person person : treeSet) {
            if (person.getEyeColor().getCode() > code) {
                count += 1;
            }
        }
        String countColor = String.valueOf(count);
        return new CommandResult(true, countColor);
    }

    public void save() {
        saveCollection();
    }

    public void saveCollection() {
        String sc = "naa";
        parser.convertToXML(this, sc);
    }


}

