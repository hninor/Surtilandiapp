package unal.edu.co.surtilandiapp.core.util;


import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogManager {
    public static String DIRECCION_ARCHIVO;
    public static String EXTENSION_ARCHIVO = ".txt";
    public static SimpleDateFormat formatoFecha = new SimpleDateFormat(
            "EEEE, MMM d, yyyy 'hora:' HH:mm:ss aaa", new Locale("es", "ES"));
    public static SimpleDateFormat formatoHora = new SimpleDateFormat(
            "HH:mm:ss aaa", new Locale("es", "ES"));
    private static String ACCION = "Acción: ";
    private static Context ctx;

    public static String generarNombreArchivoLog() {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("'log'ddMMyyyy",
                new Locale("es", "ES"));
        Date fechaActual = new Date();
        return formatoFecha.format(fechaActual);

    }

    public static void establecerEncabezadoArchivoLog(File file,
                                                      Context contexto) {
        ctx = contexto;
        if (file.exists()) {

            try {

                OutputStreamWriter osw = new OutputStreamWriter(
                        new FileOutputStream(file, true),
                        Charset.forName("UTF-8"));

                try {
                    osw.write("\n\n"
                            + formatoFecha.format(new Date())
                            + "\n"
                            + "REGISTRO LOG APLICACIÓN WMBIOMETRÍA"
                            + "\nversión "
                            + contexto.getPackageManager().getPackageInfo(
                            contexto.getPackageName(), 0).versionName
                            .toString());
                } catch (NameNotFoundException e) {
                    Log.e("LOG MANAGER",
                            "No se encontró el nombre del paquete: "
                                    + e.getMessage());
                }

                osw.close();

            } catch (IOException e) {
                Log.e("LOG MANAGER",
                        "Error de escritura al guardar en archivo log: "
                                + e.getMessage());
            }

        }

    }

    public static void guardarRegistroEnLog(String registro, Context ctx) {

        File logTBK = new File(GlobalReferences.obtenerpathlog(ctx),
                generarNombreArchivoLog() + EXTENSION_ARCHIVO);

        if (logTBK.exists()) {

            try {

                OutputStreamWriter osw = new OutputStreamWriter(
                        new FileOutputStream(logTBK, true),
                        Charset.forName("UTF-8"));

                osw.write("\n\n Hora: " + formatoHora.format(new Date()) + "\n"
                        + ACCION + registro);
                osw.close();

            } catch (IOException e) {
                Log.e("LOG MANAGER",
                        "Error de escrituro al guardar en archivo log: "
                                + e.getMessage());
            }

        } else {
            Log.e("LOG MANAGER",
                    "Se intentó guardar información en log, pero el archivo no existe");
        }

    }

}
