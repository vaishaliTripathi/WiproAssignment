package com.wipro.assignment.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by varora on 28-08-2018.
 */

public class AppUtils {
    /**
     * Get Network status
     */
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * @return true if Internert is connected else return false
     */
    public static boolean isConnectingToInternet(Context context) {
        return isNetworkAvailable(context);
    }

}
