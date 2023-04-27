package common.network;

import server.PersonCollection;

import java.io.Serializable;

public class Request<T>  implements Serializable {
    public  String command;
    public PersonCollection personCollection;
    public  T type;

    public Request(String command, T type,PersonCollection personCollection) {
        this.command = command;
        this.type = type;
        this.personCollection = personCollection;
    }
//    public Request(PersonCollection personCollection){
//        this.personCollection = personCollection;
//    }
}