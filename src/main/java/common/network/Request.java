package common.network;

import java.io.Serializable;

public class Request<T>  implements Serializable {
    public final String command;
    public final T type;

    public Request(String command, T type) {
        this.command = command;
        this.type = type;
    }
}