package com.grizzly.functions;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.InputFilter;
import android.text.Spanned;

import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Text Validation Functions. Currently contains only functions related to the Chilean ID
 * Created by fpardo on 1/28/15.
 */
public class ValidationFunctions {

    public static final String simpleRutPattern = "\\d{8}[0-9]{1}";
    public static final String commonRutPattern = "\\d{8}-?[0-9]{1}";
    public static final String fullRutPattern = "\\d{8}-?[0-9]{1}";



    /**
     * Checks if a given String is a valid Chilean ID.
     * @param rut_a the String to evaluate
     * @return true or false
     */
    public static boolean CheckRut(String rut_a) {

        boolean valor = true;
        try {

            rut_a = rut_a.toUpperCase().replaceAll("[^0-9|^K]", "");
            String digitoComprueba = String.valueOf(rut_a.charAt(rut_a.length()-1));
            String rut_c = rut_a.substring(0, rut_a.length()-1);
            String rutc = rut_c;


            int l = rutc.length()-1;
            int factor = 2;
            int acumula_rut = 0;
            String verifica = "";
            for (l = rutc.length()-1; l >= 0; l--) {
                acumula_rut = acumula_rut + factor
                        * Integer.parseInt(String.valueOf(rutc.charAt(l)));
                factor++;
                if (factor == 8) {
                    factor = 2;
                }
            }
            verifica = String.valueOf(11 - (acumula_rut % 11));
            if (11 - (acumula_rut % 11) == 10) {
                verifica = "K";
            }
            if (11 - (acumula_rut % 11) == 11) {
                verifica = "0";
            }
            if (!verifica.equalsIgnoreCase(digitoComprueba)) {
                valor = false;
            }
            return valor;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Fixes a Chilean ID, adding the proper verification string at the end of it.
     * @param rut_a The Chilean ID to be evaluated.
     * @return a fixed version of the given String.
     */
    public static String fixRut(String rut_a) {
        String rut_c = rut_a.replaceAll("\\.", "");
        String rut_o = rut_a.substring(0, 10);
        String rutc = rut_c.substring(0, 8);
        boolean valor = true;

        int l = 7;
        int factor = 2;
        int acumula_rut = 0;
        String verifica = "";
        for (l = 7; l >= 0; l--) {
            acumula_rut = acumula_rut + factor
                    * Integer.parseInt(String.valueOf(rutc.charAt(l)));
            factor++;
            if (factor == 8) {
                factor = 2;
            }
        }
        verifica = String.valueOf(11 - (acumula_rut % 11));
        if (11 - (acumula_rut % 11) == 10) {
            verifica = "K";
        }
        if (11 - (acumula_rut % 11) == 11) {
            verifica = "0";
        }

        return rut_o + verifica;
    }

    public static InputFilter filterForChileandID(){

        /*InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };*/

        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i))) {
                        return "";
                    }

                    if(Character.isLetter(source.charAt(i)) && i<7){
                        return "";
                    }
                }
                return null;
            }
        };
        return filter;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static String normalizeText(String unAccentMe){

        String s1 = Normalizer.normalize(unAccentMe, Normalizer.Form.NFKD);
        String regex = Pattern.quote("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");

        try {
            return new String(s1.replaceAll(regex, "").getBytes("ascii"), "ascii");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return unAccentMe;
        }
    }

}
