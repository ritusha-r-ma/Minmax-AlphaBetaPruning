package validation;

import utilities.ErrorHandlingUtil;

/**
 * Argument handling and validating class
 */
public class ArgValidation {
    static Boolean isVerboseEnable;
    static Boolean isABPrunEnable;
    static String rootPlayer;
    static String filePath;

    public ArgValidation(String[] arguments) {
        isABPrunEnable = false;
        isVerboseEnable = false;
        rootPlayer = "";
        filePath = "";
        readArgument(arguments);
    }

    private void readArgument(String[] args) {
        for (String arg : args) {
            if (arg.equals("-v")) { isVerboseEnable = true; }
            if (arg.equals("-ab")) { isABPrunEnable = true; }
            if (arg.equals("min") || arg.equals("max")) { rootPlayer = arg; }
            if (arg.contains(".txt")) {filePath = arg;}
        }
    }

    public static Boolean getIsVerboseEnable() {
        return isVerboseEnable;
    }

    public static Boolean getIsABPrunEnable() {
        return isABPrunEnable;
    }

    public static String getRootPlayer() { return rootPlayer; }

    public static String getFilePath() {
        return filePath;
    }
}
