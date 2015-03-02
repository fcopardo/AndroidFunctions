package com.grizzly.functions;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Patterns;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Functions related to the user's profile
 * Created by fpardo on 1/26/15.
 */
public class UserFunctions {

    @TargetApi(Build.VERSION_CODES.FROYO)
    public static String getAccount(Context context){

        AccountManager manager = AccountManager.get(context);
        Account[] accounts = manager.getAccountsByType("com.google");
        List<String> possibleEmails = new LinkedList<String>();

        for (Account account : accounts) {
            // TODO: Check possibleEmail against an email regex or treat
            // account.name as an email address only for certain account.type
            // values.
            possibleEmails.add(account.name);
        }

        if (!possibleEmails.isEmpty() && possibleEmails.get(0) != null) {
            return possibleEmails.get(0);
        } else
            return null;

    }

}
