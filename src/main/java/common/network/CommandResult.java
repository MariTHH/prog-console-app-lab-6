package common.network;

import java.io.Serializable;

/**
 * A class that is used for each command, checking its status
 */
public class CommandResult implements Serializable {
    public boolean status;
    public final String message;

    public CommandResult(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

}

