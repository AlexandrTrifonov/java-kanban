package exceptions;

import java.io.IOException;

public class ManagerSaveException extends RuntimeException {
    private final String err;
    public ManagerSaveException(String err, IOException e) {
        this.err = err;
    }
}