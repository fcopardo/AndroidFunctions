package com.grizzly.functions;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by FcoPardo on 7/2/15.
 */
public class ResourceFunctions {

    public static int getDrawableResId(String resName, Context context) {

        int resourceId;
        try {
            Resources resources = context.getApplicationContext().getResources();
            resourceId = resources.getIdentifier(resName, "drawable",
                    context.getApplicationContext().getPackageName());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return -1;
        }

        return resourceId;
    }

    public static Drawable getDrawable(String name, Context context) {
        int resourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return context.getResources().getDrawable(resourceId);
    }

    public static int getStringFrom(String resName, Context context, String valuesLocaleCode) {

        int resourceId;
        try {
            Resources resources = context.getApplicationContext().getResources();
            resourceId = resources.getIdentifier(resName, "values"+valuesLocaleCode,
                    context.getApplicationContext().getPackageName());
            System.out.println("Found a resource: "+resources.getString(resourceId));
        } catch (NullPointerException e) {
            e.printStackTrace();
            return -1;
        }

        return resourceId;
    }

    public static String getString(String resName, Context context) {

        int resourceId;
        String text = "";
        try {
            Resources resources = context.getApplicationContext().getResources();
            resourceId = resources.getIdentifier(resName, "values",
                    context.getApplicationContext().getPackageName());
            text = resources.getString(resourceId);
            System.out.println("Found a resource: " + text);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return text;
    }



}
