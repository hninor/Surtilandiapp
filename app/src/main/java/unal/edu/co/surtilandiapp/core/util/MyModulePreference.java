package unal.edu.co.surtilandiapp.core.util;

import android.content.Context;

import net.grandcentrix.tray.TrayPreferences;

/**
 * Created by hnino on 29/11/2017.
 */

// create a preference accessor for a module
public class MyModulePreference extends TrayPreferences {

    public static String USER = "USER";
    public static String STORE = "STORE";

    public MyModulePreference(final Context context) {
        super(context, "myModule", 1);
    }
}
