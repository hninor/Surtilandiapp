package unal.edu.co.surtilandiapp.core.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class GlobalReferences {
    public static File obtenerpath(Context contexto) {

        File name = new File(Environment.getExternalStorageDirectory() + "/dataOld/"
                + contexto.getApplicationContext().getPackageName() + "/Files/");

        if (!name.exists()) {
            name.mkdirs();
        }
        return name;
    }

    public static File obtenerPathMer(Context contexto) {

        File name = new File(Environment.getExternalStorageDirectory() + "/dataOld/"
                + contexto.getApplicationContext().getPackageName() + "/MER/");

        if (!name.exists()) {
            name.mkdirs();
        }
        return name;
    }

    public static File obtenerPathDB(Context contexto) {

        File name = new File("/dataOld/dataOld/" + contexto.getPackageName() + "/databases/wmsmp-db");

        if (!name.exists()) {
            name.mkdirs();
        }
        return name;
    }

    public static String obtenerpathlog(Context contexto) {

        File name = new File(Environment.getExternalStorageDirectory() + "/data/"
                + contexto.getApplicationContext().getPackageName() + "/Logs/");

        if (!name.exists()) {
            name.mkdirs();
        }
        return name.toString();
    }
}
