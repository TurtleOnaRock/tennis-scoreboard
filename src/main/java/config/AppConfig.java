package config;

import org.hibernate.boot.cfgxml.internal.ConfigLoader;

import java.io.InputStream;
import java.util.Properties;

public final class AppConfig {

    public static final String PROPERTIES_FILE_NAME = "config.properties";

    public static final String PLAYER_NAME_MAX_LENGTH = "playerNameMaxLength";
    public static final String FINISHED_MATCHES_TABLE_ROW_SIZE = "finishedMatchesTableRowSize";
    public static final String FORBIDDEN_CHARS = "forbiddenChars";
    public static final String AMOUNT_SETS_TO_WIN = "amountSetsToWin";

    public static final int DEFAULT_NAME_MAX_LANGTH = 30;
    public static final int DEFAULT_PAGE_SIZE = 5;
    public static final int DEFAULT_AMOUNT_SET_TO_WIN = 3;
    public static final String DEFAULT_FORBIDDEN_CHARS = "!?\";({[')}]";

    public static String getForbiddenChars() {
        try {
            return getProperty(FORBIDDEN_CHARS);
        } catch (Exception e) {
            return DEFAULT_FORBIDDEN_CHARS;
        }
    }

    public static int getTableRowSize() {
        int result;
        try {
            String parameter = getProperty(FINISHED_MATCHES_TABLE_ROW_SIZE);
            result = Integer.parseInt(parameter);
        } catch (Exception e) {
            result = DEFAULT_PAGE_SIZE;
        }
        return result;
    }

    public static int getPlayerNameLength() {
        int result;
        try {
            String parameter = getProperty(PLAYER_NAME_MAX_LENGTH);
            result = Integer.parseInt(parameter);
        } catch (Exception e) {
            result = DEFAULT_NAME_MAX_LANGTH;
        }
        return result;
    }

    public static int getAmountSetsToWin() {
        int result;
        try {
            String parameter = getProperty(AMOUNT_SETS_TO_WIN);
            result = Integer.parseInt(parameter);
        } catch (Exception e) {
            result = DEFAULT_AMOUNT_SET_TO_WIN;
        }
        return result;
    }

    private static String getProperty(String key) throws Exception {
        Properties properties = new Properties();
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            properties.load(input);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }
        return properties.getProperty(key);
    }
}
