package server;

import client.ClientManager;
import common.Person;

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
public class PersonCollection {
    @XmlElement(name = "Person")
    private String filename;
    private TreeSet<Person> treeSet = new TreeSet<>();
    private static Date creationDate = new Date();


    public PersonCollection(TreeSet<Person> treeSet, String filename) {
        this.treeSet = treeSet;
        this.filename = filename;
    }


    public PersonCollection() {
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

    /**
     * displays information about each person
     */
    public void information() {
        if (treeSet.isEmpty()) {
            System.out.println("В коллекции ничего нет");
        } else
            for (Person person : treeSet) {
                personInfo(person);
            }
    }

    /**
     * adds a person if he is lower than the other
     *
     * @param sc
     */
    public void addIfMin(String sc) {
        Scanner scanner = new Scanner(System.in);
        String height_s = sc.trim();
        int height_int = Integer.parseInt(height_s);
        boolean flag = false;
        for (Person person1 : treeSet) {
            flag = height_int < person1.getHeight();

        }
        if (flag) {
            Person person = ClientManager.getNewPerson(scanner);
            addPerson(person);
            person.setHeight(height_int);
            System.out.println("Самый низкий персонаж добавлен");
        } else {
            System.out.println("Персонаж не ниже всех");
        }
    }


    /**
     * adds a person if he is higher than the other
     *
     * @param sc
     */
    public void addIfMax(String sc) {
        Scanner scanner = new Scanner(System.in);
        String height_s = sc.trim();
        int height_int = Integer.parseInt(height_s);
        boolean flag = false;
        for (Person person1 : treeSet) {
            flag = height_int > person1.getHeight();

        }
        if (flag) {
            Person person = ClientManager.getNewPerson(scanner);
            addPerson(person);
            person.setHeight(height_int);
            System.out.println("Самый высокий персонаж добавлен");
        } else {
            System.out.println("Персонаж не выше всех");
        }
    }

    /**
     * clears the collection
     */
    public void clearCollection() {
        treeSet.clear();
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
    public void info() {
        System.out.println(treeSet.getClass().getName() + " " + PersonCollection.creationDate + " " + treeSet.size());
    }

    /**
     * print information about available commands
     */
    public static void help() {
        System.out.println("add {element} : добавить новый элемент в коллекцию \n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции \n" +
                "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции \n" +
                "clear : очистить коллекцию \n" +
                "count_greater_than_eye_color eyeColor : вывести количество элементов, значение поля eyeColor которых больше заданного \n" +
                "filter_greater_than_location location : вывести элементы, значение поля location которых больше заданного \n" +
                "help : вывести справку по доступным командам \n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов) \n" +
                "print_unique_location : вывести уникальные значения поля location всех элементов в коллекции \n" +
                "remove_by_id id : удалить элемент из коллекции по его id \n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный \n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении \n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному"
        );
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

