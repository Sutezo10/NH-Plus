package utils;

public class GeneralCheckMethods {

    public static boolean checkLetterInput(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkNumberInput(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
