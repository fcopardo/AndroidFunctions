
package com.grizzly.functions;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Functions to format text
 *
 * @author Fco Pardo Baeza.
 */
public class TextFunctions {

    /**
     * Sets the first character of a String to Uppercase.
     * @param toCase
     * @return
     */
    public static String setFirstUCase(String toCase) {
        if (toCase.length() > 1) {
            toCase = toCase.substring(0, 1).toUpperCase()
                    + toCase.substring(1, toCase.length()).toLowerCase();
        }
        return toCase;
    }

    /**
     * Sets the first character of all the words in a string to Uppercase, like a name.
     * @param toCase the string to be processed
     * @return a String with all the first characters in uppercase.
     */
    public static String setAsName(String toCase) {

        String word = "";
        String character = "";
        if (toCase.length() > 1) {

            for (int c = 0; c < toCase.length(); c++) {
                if (c == 0) {
                    character = String.valueOf(toCase.charAt(c)).toUpperCase();
                } else {
                    if (character.equalsIgnoreCase(" ")) {
                        word = word + character;
                        character = String.valueOf(toCase.charAt(c)).toUpperCase();
                    } else {
                        word = word + character;
                        character = String.valueOf(toCase.charAt(c));
                    }
                }
            }
            word = word + character;
            word = word.trim();
            return word;
        } else {
            return toCase.toUpperCase();
        }
    }

    /**
     * Creates a SHA-256 hash from a given string.
     * @param password the string to be hashed.
     * @return a String representing the SHA-256 form of the argument.
     * @throws java.security.NoSuchAlgorithmException if the SHA-256 algorithm is absent from the JVM.
     */
    public static String getHash256(String password)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return sb.toString();
    }

    /**
     * Creates a SHA-1 hash from a given string.
     * @param password the string to be hashed.
     * @return a String representing the SHA-1 form of the argument.
     * @throws java.security.NoSuchAlgorithmException if the SHA-1 algorithm is absent from the JVM.
     */
    public static String getHashOne(String password)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return sb.toString();
    }

    /**
     * Evaluates if a String is null/empty or not.
     * @param str the String to be evaluated.
     * @return true if the String is null or empty.
     */
    public static boolean isBlankOrNull(String str) {
        return (str == null || "".equals(str.trim()));
    }

    /**
     * Evaluates if a given String is an email.
     * @param mail
     * @return
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static boolean isMail(String mail){
        return !TextUtils.isEmpty(mail) && android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    /**
     * Allows to get the id of a value inside a given R class. Useful to traverse the dictionary.
     * @param rClass the R class to be traversed. Example: R.String.class
     * @param resourceText the resource which id is being looked for.
     * @return the int identifier of the resource. 0 if the operation fails.
     */
    public static int getResourceId(Class rClass, String resourceText){
        return getResourceId(rClass, resourceText, true);
    }

    /**
     * Allows to get the id of a value inside a given R class. Useful to traverse the dictionary.
     * @param rClass the R class to be traversed. Example: R.String.class
     * @param resourceText the resource which id is being looked for.
     * @return the int identifier of the resource. 0 if the operation fails.
     */
    public static int getResourceId(Class rClass, String resourceText, boolean showExceptions){
        try {
            return rClass.getDeclaredField(resourceText).getInt(null);
        } catch (IllegalAccessException e) {
            if(showExceptions) e.printStackTrace();
        } catch (NoSuchFieldException e) {
            if(showExceptions) e.printStackTrace();
        }
        return 0;
    }
}
