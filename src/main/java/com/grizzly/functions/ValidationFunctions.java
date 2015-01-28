package com.grizzly.functions;

/**
 * Created by fpardo on 1/28/15.
 */
public class ValidationFunctions {

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
                System.out.println("ValidaRut "+String.valueOf(rutc.charAt(l)));
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
                System.out.println("ValidaRut2,"+ verifica+"=="+digitoComprueba);
            }
            return valor;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
}
