
package com.grizzly.functions;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationFunctions {


    public static final int MESSAGE_ERROR = 0;
    public static final int MESSAGE_ALERT = 1;
    public static final int MESSAGE_INFO  = 2;
    public static final int MESSAGE_GOOD  = 3;

    private static int error_icon = 0;
    private static int alert_icon = 0;
    private static int info_icon = 0;
    private static int good_icon = 0;
    private static int company_logo = 0;

    /**
     * Shows a message in a custom toast
     *
     * @param Message  the message to be show
     * @param activity an activity to provide access to android's services
     */
    public static void ShowMessage(String Message, Activity activity) {

        ShowMessage(Message, activity.getApplicationContext());

    }

    /**
     * Shows a message in a custom toast
     *
     * @param Message  the message to be show
     * @param activity an activity to provide access to android's services
     */
    public static void ShowMessage(String Message, Activity activity, int messageType) {

        ShowMessage(Message, activity.getApplicationContext(), messageType);

    }

    /**
     * Shows a message in a custom toast. It has a logo, a title and a body.
     *
     * @param Message  the message body to be show.
     * @param context  an activity to provide access to android's services
     * @param Title    a title for the toast.
     */
    public static void ShowMessage(String Message, String Title, Activity activity) {

        ShowMessage(Message, Title, activity.getApplicationContext());
    }

    /**
     * Shows a message in a custom toast. It has a logo, a title and a body.
     *
     * @param Message  the message body to be show.
     * @param context  an activity to provide access to android's services
     * @param Title    a title for the toast.
     */
    public static void ShowMessage(String Message, String Title, Activity activity, int messageType) {

        ShowMessage(Message, Title, activity.getApplicationContext(), messageType);
    }

    /**
     * Shows a message in a custom toast
     *
     * @param Message  the message to be show
     * @param context  a context to provide access to android's services
     * @param type     0 to show the message a long time.
     */
    public static void ShowMessage(String Message, Context context) {

        try {

            LayoutInflater inflater = LayoutInflater.from(context);
            View layout = inflater.inflate(R.layout.toast_layout, null);
            TextView title = (TextView) layout.findViewById(R.id.toast_txt_title);
            ImageView logo = (ImageView) layout.findViewById(R.id.toast_img_logo);

            title.setText(Message);

            if(company_logo!=0){
                logo.setImageResource(company_logo);
            }
            Toast toast = new Toast(context);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

        } catch (Exception customToastNotPossible) {
            customToastNotPossible.printStackTrace();

            Toast toast = new Toast(context);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Shows a message in a custom toast
     *
     * @param Message  the message to be show
     * @param context  a context to provide access to android's services
     * @param type     0 to show the message a long time.
     */
    public static void ShowMessage(String Message, Context context, int messageType) {

        try {

            LayoutInflater inflater = LayoutInflater.from(context);
            View layout = inflater.inflate(R.layout.toast_layout, null);
            TextView title = (TextView) layout.findViewById(R.id.toast_txt_title);
            ImageView logo = (ImageView) layout.findViewById(R.id.toast_img_logo);

            switch(messageType){
                case MESSAGE_ALERT: if(alert_icon!=0)logo.setImageResource(alert_icon);
                    break;
                case MESSAGE_ERROR: if(error_icon!=0)logo.setImageResource(error_icon);
                    break;
                case MESSAGE_INFO: if(info_icon!=0)logo.setImageResource(info_icon);
                    break;
                case MESSAGE_GOOD: if(good_icon!=0)logo.setImageResource(good_icon);
                    break;
                default:break;
            }

            title.setText(Message);

            Toast toast = new Toast(context);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

        } catch (Exception customToastNotPossible) {
            customToastNotPossible.printStackTrace();

            Toast.makeText(context, Message,
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Shows a message in a custom toast. It has a logo, a title and a body.
     *
     * @param Message  the message body to be show.
     * @param context  a context to provide access to android's services
     * @param Title    a title for the toast.
     */
    public static void ShowMessage(String Message, String Title, Context context) {

        try {

            LayoutInflater inflater = LayoutInflater.from(context);
            View layout = inflater.inflate(R.layout.toast_big_layout, null);
            TextView title = (TextView) layout.findViewById(R.id.toast_big_txt_title);
            TextView text = (TextView) layout.findViewById(R.id.toast_big_txt_body);

            title.setText(Title);
            text.setText(Message);

            Toast toast = new Toast(context);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

        } catch (Exception customToastNotPossible) {
            customToastNotPossible.printStackTrace();

            Toast.makeText(context, Message,
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Shows a message in a custom toast. It has a logo, a title and a body.
     *
     * @param Message  the message body to be show.
     * @param context  a context to provide access to android's services
     * @param Title    a title for the toast.
     */
    public static void ShowMessage(String Message, String Title, Context context, int messageType) {

        try {

            LayoutInflater inflater = LayoutInflater.from(context);
            View layout = inflater.inflate(R.layout.toast_big_layout, null);
            TextView title = (TextView) layout.findViewById(R.id.toast_big_txt_title);
            TextView text = (TextView) layout.findViewById(R.id.toast_big_txt_body);

            ImageView logo = (ImageView) layout.findViewById(R.id.toast_img_logo);

            switch(messageType){
                case MESSAGE_ALERT: if(alert_icon!=0)logo.setImageResource(alert_icon);
                    break;
                case MESSAGE_ERROR: if(error_icon!=0)logo.setImageResource(error_icon);
                    break;
                case MESSAGE_INFO: if(info_icon!=0)logo.setImageResource(info_icon);
                    break;
                case MESSAGE_GOOD: if(good_icon!=0)logo.setImageResource(good_icon);
                    break;
            }


            title.setText(Title);
            text.setText(Message);

            Toast toast = new Toast(context);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

        } catch (Exception customToastNotPossible) {
            customToastNotPossible.printStackTrace();

            Toast.makeText(context, Message,
                    Toast.LENGTH_LONG).show();
        }
    }

    public static void setError_icon(@DrawableRes int error_icon) {
        NotificationFunctions.error_icon = error_icon;
    }

    public static void setAlert_icon(@DrawableRes int alert_icon) {
        NotificationFunctions.alert_icon = alert_icon;
    }

    public static void setInfo_icon(@DrawableRes int info_icon) {
        NotificationFunctions.info_icon = info_icon;
    }

    public static void setGood_icon(@DrawableRes int good_icon) {
        NotificationFunctions.good_icon = good_icon;
    }

    public static void setCompany_logo(@DrawableRes int company_logo) {
        NotificationFunctions.company_logo = company_logo;
    }
}
