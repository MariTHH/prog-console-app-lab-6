package common.network;

import server.PersonCollection;

import java.io.Serializable;

/**
 * the class that is responsible for requests
 * @param <T>
 */
public class Request<T> implements Serializable {
    public String command;
    public PersonCollection personCollection;
    public T type;

    public Request(String command, T type, PersonCollection personCollection) {
        this.command = command;
        this.type = type;
        this.personCollection = personCollection;
    }
}