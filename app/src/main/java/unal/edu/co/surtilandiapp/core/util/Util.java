package unal.edu.co.surtilandiapp.core.util;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by nprada on 01/12/2016.
 */

public class Util {

    //public static String URL_SERVICIO = "http://win-tst.wi-mobile.com/wm/wmCommunicator/Manner.svc/";
    public static String URL_SERVICIO = "http://win-tst.wi-mobile.com/Servicios/SmartRecovery/WMCommunicator/Manner.svc/";
    //public static String URL_SERVICIO = "http://192.168.0.147/wm/wmCommunicator/Manner.svc/";
    //public static String URL_SERVICIO_PRUEBA = "http://win-tst.wi-mobile.com/wm/wmCommunicator/Manner.svc";
    public static String URL_SERVICIO_PRUEBA = "http://win-tst.wi-mobile.com/Servicios/SmartRecovery/WMCommunicator/Manner.svc";

    public static String getDate() {
        Date date = new Date();
        String formattedDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
        return formattedDate;
    }

    public static String getDateFoto(){

        try {

            Date dateNow = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_mm_dd_hh_mm_ss");
            String newDateString = formatter.format(dateNow);

            return newDateString;

        }catch (Exception e){
            e.printStackTrace();
        }

        return "No Fecha";
    }

    public static String getAppVersion(Context c) {
        PackageManager manager = c.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(c.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionName;
    }

    public static String getTimeStamp() {
        Long tsLong = System.currentTimeMillis() / 1000;
        return tsLong.toString();
    }

    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public static String getMacAddress(Context context) {
        WifiManager wimanager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        String macAddress = wimanager.getConnectionInfo().getMacAddress();
        if (macAddress == null) {
            macAddress = "Device don't have mac address or wi-fi is disabled";
        }
        return macAddress;
    }

    public static String getIp(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }

    public static String obtenerPathGrabacionHuellas(Context contexto) {

        File name = new File(Environment.getExternalStorageDirectory() + "/data/"
                + contexto.getApplicationContext().getPackageName() + "/Huellas/");

        if (!name.exists()) {
            name.mkdirs();
        }
        return name.toString();
    }



    public static String convertDate(String fechaRara) {
        String fecha = fechaRara.replaceFirst("Date", "");
        try {
            fecha = fecha.replaceAll("[^a-zA-Z0-9]", "");
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Long f = Long.parseLong(fecha) / 10000;
            Date date1 = new Date(f);
            fecha = format.format(date1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fecha;
    }

}
