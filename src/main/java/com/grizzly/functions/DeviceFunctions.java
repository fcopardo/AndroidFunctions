

package com.grizzly.functions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


/**
 * Created on 20-03-14.
 */
public class DeviceFunctions {


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point giveScreenResolution(Activity activity){

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * Allows to know if the device's screen is big (tablet size) or not.
     *
     * @param context an activity's context to access to the resources.
     * @return true if the device's screen is small or normal (phone size).
     */
    public static boolean isNormalOrSmallResolution(Context context) {
        boolean isNormalOrSmallResolution = false;
        if (context != null) {
            if (((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL)
                    || ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL)) {
                isNormalOrSmallResolution = true;
            }
        }
        return isNormalOrSmallResolution;
    }

    /**
     * Allows to detect if the device's screen size is small.
     *
     * @param context an activity's context to access to the resources.
     * @return true if the device is small
     */
    public static boolean isSmallResolution(Context context) {
        boolean isNormalOrSmallResolution = false;
        if (context != null) {
            if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
                isNormalOrSmallResolution = true;
            }
        }
        return isNormalOrSmallResolution;
    }

    /**
     * Allows to detect the screen orientation
     *
     * @param act an activity to provide access to the android system's services
     * @return true if the device is in landscape mode
     */
    public static boolean isLandscape(Activity act) {
        boolean bol = false;
        Display display = ((WindowManager) act
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        int orientation = display.getRotation();

        if (orientation == Surface.ROTATION_90
                || orientation == Surface.ROTATION_270) {
            bol = true;
        }
        return bol;
    }

    public static boolean isJellyBeanDevice(){
        if(!(Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2 &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)){
            return false;
        }
        else return true;
    }

    public static boolean isDeviceSmall(Context context){

        int screenLayout = context.getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenLayout) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return true;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return false;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return false;
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                return false;
            default:
                return false;
        }

    }

    /**
     * Return the Device IP address.
     * Source: https://groups.google.com/forum/#!topic/android-developers/a5loFkRuV3w
     * Credits to Regina Mitsue Azuma
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en =
                 NetworkInterface.getNetworkInterfaces(); en
                         .hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr =
                     intf.getInetAddresses(); enumIpAddr
                             .hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement
                            ();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString
                                ();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("AndroidFunctions", ex.toString());
        }
        return null;
    }

}
