package exceptions;

import jakarta.servlet.ServletException;

public class WrongPlayerException extends ServletException {
    public WrongPlayerException(String message) {
        super(message);
    }
}
