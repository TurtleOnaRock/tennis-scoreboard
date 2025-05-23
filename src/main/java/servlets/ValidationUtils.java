package servlets;

import exceptions.IncorrectParameterException;

public class ValidationUtils {

    public static void validateParameter(String parameter, int maxLenth, String forbiddenChar) throws IncorrectParameterException {
        if (parameter == null || parameter.isEmpty()) {
            throw new IncorrectParameterException("Введите имя игрока.");
        }
        if (parameter.length() > maxLenth) {
            throw new IncorrectParameterException(parameter + "\n Cлишком длинное имя. Имя должно содержать не более " + maxLenth + " символов");
        }
        if (containsForbiddenChar(parameter, forbiddenChar)) {
            throw new IncorrectParameterException("Имя не должно содержать знаков: " + forbiddenChar);
        }
    }

    private static boolean containsForbiddenChar(String input, String forbiddenChar) {
        String regex = "[" + forbiddenChar + "]";
        return input.matches(".*" + regex + ".*");
    }

}
