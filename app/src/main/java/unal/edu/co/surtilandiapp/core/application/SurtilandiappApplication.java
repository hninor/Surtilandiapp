package unal.edu.co.surtilandiapp.core.application;

import android.app.Application;
import android.telephony.TelephonyManager;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import java.io.File;

import unal.edu.co.surtilandiapp.core.data.database.greendao.DaoMaster;
import unal.edu.co.surtilandiapp.core.data.database.greendao.DaoSession;
import unal.edu.co.surtilandiapp.core.util.GlobalReferences;
import unal.edu.co.surtilandiapp.core.util.LogManager;

/**
 * Created by gsanchez on 25/11/2016.
 */

public class SurtilandiappApplication extends Application {

    private static final String TAG = SurtilandiappApplication.class.getName();
    public static final boolean ENCRYPTED = false;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "db-biometria-cifrada" : "db-biometria");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        try {

            File logTBK = new File(GlobalReferences.obtenerpathlog(getApplicationContext()),
                    LogManager.generarNombreArchivoLog() + LogManager.EXTENSION_ARCHIVO);

            if (!logTBK.exists()) {

                logTBK.createNewFile();
                LogManager.establecerEncabezadoArchivoLog(logTBK, getApplicationContext());

            }

        } catch (Exception e) {
            Log.e("CREACION LOG", e.getMessage());
        }
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public String getIMEI() {

        TelephonyManager telephonyManager;
        telephonyManager = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();

    }

}
