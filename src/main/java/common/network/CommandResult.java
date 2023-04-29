package common.network;

import server.PersonCollection;

import java.io.Serializable;

/**
 * A class that is used for each command, checking its status
 */
public class CommandResult implements Serializable {
    public boolean status;
    public final String message;
    private PersonCollection personCollection = null;
    public CommandResult(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public PersonCollection getPersonCollection() {
        return personCollection;
    }

    public void setPersonCollection(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }
}

