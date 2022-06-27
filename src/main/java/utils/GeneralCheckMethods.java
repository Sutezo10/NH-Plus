package utils;


/**
 * This class provides static methods, that are used to check inputs for the needed type
 */
public class GeneralCheckMethods {


    /**
     *
     * Checks, if the input contains only Letters
     *
     * @param str input from the field, which is getting checked
     * @return a boolean depending on the check state
     */
    public static boolean checkLetterInput(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * Checks, if the input contains only Numbers
     *
     * @param str input from the field, which is getting checked
     * @return a boolean depending on the check state
     */
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
