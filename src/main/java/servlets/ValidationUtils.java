package servlets;

public class ValidationUtils {

    public static boolean containsForbiddenChars(String input, String forbiddenChars) {
        String regex = "[" + forbiddenChars + "]";
        return input.matches(".*" + regex + ".*");
    }

    public static boolean isLonger(String input, int maxLength) {
        return input.length() > maxLength;
    }

    public static boolean isNothing(String input) {
        return (input == null || input.isEmpty());
    }

    public static int chooseValueOrMinMax(int value, int minValue, int maxValue) {
        if (value < minValue) {
            return minValue;
        }
        if (value > maxValue) {
            return maxValue;
        }
        return value;
    }

    public static boolean onlyDigits(String parameter) {
        return parameter.matches("[0123456789]+");
    }

    public static int getIntNumberOrDefault(String parameter, int defaultNumber) {
        if (isNothing(parameter)) {
            return defaultNumber;
        }
        if (!onlyDigits(parameter)) {
            return defaultNumber;
        }
        int number;
        try {
            number = Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            return defaultNumber;
        }
        return number;
    }

}