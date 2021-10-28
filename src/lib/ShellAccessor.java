package lib;

import java.util.regex.PatternSyntaxException;

public class ShellAccessor {


    // Reset the BG and FG color
    public static final String ANSI_RESET = "\u001B[0m";

    // All FG colors
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";
    public static final String ANSI_BRIGHT_RED = "\u001B[91m";
    public static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
    public static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
    public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
    public static final String ANSI_BRIGHT_WHITE = "\u001B[97m";

    // All BG colors
    public static final String ANSI_BG_BLACK = "\u001B[40m";
    public static final String ANSI_BG_RED = "\u001B[41m";
    public static final String ANSI_BG_GREEN = "\u001B[42m";
    public static final String ANSI_BG_YELLOW = "\u001B[43m";
    public static final String ANSI_BG_BLUE = "\u001B[44m";
    public static final String ANSI_BG_PURPLE = "\u001B[45m";
    public static final String ANSI_BG_CYAN = "\u001B[46m";
    public static final String ANSI_BG_WHITE = "\u001B[47m";

    public static final String ANSI_BRIGHT_BG_BLACK = "\u001B[100m";
    public static final String ANSI_BRIGHT_BG_RED = "\u001B[101m";
    public static final String ANSI_BRIGHT_BG_GREEN = "\u001B[102m";
    public static final String ANSI_BRIGHT_BG_YELLOW = "\u001B[103m";
    public static final String ANSI_BRIGHT_BG_BLUE = "\u001B[104m";
    public static final String ANSI_BRIGHT_BG_PURPLE = "\u001B[105m";
    public static final String ANSI_BRIGHT_BG_CYAN = "\u001B[106m";
    public static final String ANSI_BRIGHT_BG_WHITE = "\u001B[107m";


    final private static String[] mainKeywords = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "class", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile"};


    public static String checkAndColor(String input) {
        String temp = input;


        for (String mainKeyword : mainKeywords) {
            temp = temp.replaceAll(mainKeyword, colorString(mainKeyword, ANSI_BLUE, ""));
        }

        int index1 = 0;
        int index2 = 0;
        int timesAStringIndication = 0;
        int timesACharIndication = 0;

        for (int i = 0; i < input.length(); i++) {

            if (input.toCharArray()[i] == '\"') {
                timesAStringIndication++;
                index1 = index2;
                index2 = i;
            }
            if (timesAStringIndication == 2) {
                try {
                    temp = temp.replaceAll(input.substring(index1, index2 + 1), colorString(input.substring(index1, index2 + 1), ANSI_GREEN, ""));
                } catch (PatternSyntaxException patternSyntaxException) {
                    patternSyntaxException.printStackTrace();
                }

                timesAStringIndication = 0;
            }
        }

        index1 = 0;
        index2 = 0;


        for (int i = 0; i < input.length(); i++) {

            if (input.toCharArray()[i] == '\'') {
                timesACharIndication++;
                index1 = index2;
                index2 = i;
            }
            if (timesACharIndication == 2) {
                try {
                    temp = temp.replaceAll(input.substring(index1, index2 + 1), colorString(input.substring(index1, index2 + 1), ANSI_GREEN, ""));
                } catch (PatternSyntaxException patternSyntaxException) {
                    patternSyntaxException.printStackTrace();
                }

                timesACharIndication = 0;
            }
        }


        return temp;
    }


    public static void colorPrint(String text, String FG, String BG) {
        System.out.print(FG + BG + text + ANSI_RESET);
    }

    public static String colorString(String text, String FG, String BG) {
        return (FG + BG + text + ANSI_RESET);
    }
}
