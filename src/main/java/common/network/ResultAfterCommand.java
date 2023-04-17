package common.network;

import java.io.Serializable;

public class ResultAfterCommand  implements Serializable {
    public boolean status;
    public final String message;

    public ResultAfterCommand(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}

