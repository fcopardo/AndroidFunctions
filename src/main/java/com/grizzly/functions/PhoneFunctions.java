

package com.grizzly.functions;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created on 20-03-14.
 */
public class PhoneFunctions {

    /**
     * Sends a SMS, or a SMS chain if the message is too long.
     *
     * @param list
     * @param number
     */
    @TargetApi(Build.VERSION_CODES.FROYO)
    public static void sendSMS(String list, String number) {
        if (!number.equalsIgnoreCase("null") && !number.isEmpty()) {
            android.telephony.SmsManager m = android.telephony.SmsManager
                    .getDefault();
            if (number.length() < 10) {
                number = "+569" + number;
            }
            if (list.length() >= 160) {
                m.sendMultipartTextMessage(number, null,
                        m.divideMessage(list), null, null);
            } else {
                m.sendTextMessage(number, null, list, null, null);
            }
        }
    }

    /**
     * Sends a message list, composed by multiple SMSs
     *
     * @param list   the messages to send.
     * @param number
     */
    @TargetApi(Build.VERSION_CODES.FROYO)
    public static void sendMultiSMS(String list, String number) {
        android.telephony.SmsManager m = android.telephony.SmsManager
                .getDefault();
        if (number.length() < 10) {
            number = "+569" + number;
        }
        m.sendMultipartTextMessage(number, null, m.divideMessage(list), null,
                null);
    }

    /**
     * Sends a SMS
     *
     * @param list
     * @param number
     */
    @TargetApi(Build.VERSION_CODES.FROYO)
    public static void sendSingleSMS(String list, String number) {
        android.telephony.SmsManager m = android.telephony.SmsManager
                .getDefault();
        if (number.length() < 10) {
            number = "+569" + number;
        }
        m.sendTextMessage(number, null, list, null, null);
    }
}
