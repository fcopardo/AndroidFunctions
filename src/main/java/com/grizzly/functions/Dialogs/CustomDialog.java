/*
 * Copyright (c) 2014. Fco Pardo Baeza.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.grizzly.functions.Dialogs;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.grizzly.functions.R;

/**
 * A custom dialog.
 *
 * @author
 */
public class CustomDialog {

    private static String response = "";
    private static boolean booleanResponse = false;
    private static int logo = 0;

    @TargetApi(Build.VERSION_CODES.FROYO)
    public static void OneButtonDialog(final Activity activity, String message) {

        AlertDialog alertDialog1 = new AlertDialog.Builder(activity).create();

        if(logo!=0){
            alertDialog1.setIcon(logo);
        }

        alertDialog1.setTitle("Info");
        alertDialog1.setMessage(message);
        alertDialog1.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                    }
                }
        );
        alertDialog1.show();
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    public static void OneButtonDialog(final Activity activity, String message,
                                       String title) {

        AlertDialog alertDialog1 = new AlertDialog.Builder(activity).create();

        alertDialog1.setTitle(title);
        alertDialog1.setMessage(message);
        alertDialog1.setIcon(logo);
        alertDialog1.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                    }
                }
        );
        alertDialog1.show();
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    public static String getEmailDialog(Activity activity) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View textEntryView = inflater.inflate(R.layout.dialog_email, null);
        String returnString = "";

        builder.setView(textEntryView).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                try {
                    final EditText email = (EditText) textEntryView.findViewById(R.id.dialogEmail_email);
                    CustomDialog.setResponse(email.getText().toString());

                } catch (Exception e) {
                    e.printStackTrace();
                    CustomDialog.setResponse("");
                    dialog.dismiss();
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                CustomDialog.setResponse("false01rt-");
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setTitle(activity.getString(R.string.getEmailMessage));
        if(logo!=0){
            dialog.setIcon(logo);
        }
        dialog.show();

        returnString = CustomDialog.getResponse();
        CustomDialog.setResponse("");
        return returnString;
    }

    public static boolean getBooleanDialog(Activity activity, String question, String title){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle(title);
        builder.setMessage(question);

        builder.setPositiveButton(activity.getString(R.string.Yes), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                setBooleanResponse(true);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(activity.getString(R.string.No), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                setBooleanResponse(false);
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

        boolean valueToReturn = getBooleanResponse();
        setBooleanResponse(false);
        return valueToReturn;
    }

    private static String getResponse() {
        return response;
    }

    private static void setResponse(String response) {
        CustomDialog.response = response;
    }

    private static void setBooleanResponse(boolean bol) {
        CustomDialog.booleanResponse = bol;
    }

    private static boolean getBooleanResponse() {
        return booleanResponse;
    }

    public static int getLogo() {
        return logo;
    }

    public static void setLogo(@DrawableRes int logo) {
        CustomDialog.logo = logo;
    }
}
