package exceptions;

import jakarta.servlet.ServletException;

public class IncorrectParameterException extends ServletException {
    public IncorrectParameterException(String message) {
        super(message);
    }
}
