package servlets;

import exceptions.IncorrectParameterException;

public class ValidationUtils {

    public static void validateParameter(String parameter, int maxLenth, String forbiddenChar) throws IncorrectParameterException {
        if (parameter == null || parameter.isEmpty()) {
            throw new IncorrectParameterException("Enter a player's name");
        }
        if (parameter.length() > maxLenth) {
            throw new IncorrectParameterException(parameter + "\n is too long! Player's name should consist of less than" + maxLenth + " characters");
        }
        if (containsForbiddenChar(parameter, forbiddenChar)) {
            throw new IncorrectParameterException("Player's name might not include followed characters: " + forbiddenChar);
        }
    }

    private static boolean containsForbiddenChar(String input, String forbiddenChar) {
        String regex = "[" + forbiddenChar + "]";
        return input.matches(".*" + regex + ".*");
    }

}

