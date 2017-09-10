package unal.edu.co.surtilandiapp.core.data.database.mapper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import unal.edu.co.surtilandiapp.core.data.database.greendao.DaoMaster;
import unal.edu.co.surtilandiapp.core.data.database.greendao.DaoSession;


public class MainMapper {
      public static final short MATERIALES_CLASS = 1;
      public static final short ORDENTRABAJO_CLASS = 2;
      public static final short LECTURASMEDIDOR_CLASS = 3;
      public static final short ULTIMOSCONSUMOS_CLASS = 4;
      public static final short LISTAS_CLASS = 5;
      public static final short ACOMETIDA_CLASS = 6;
      public static final short SELLO_CLASS = 7;
      public static final short USUARIO_CLASS = 8;
      public static final short ORDENTRABAJOCAMPOS_CLASS = 9;

   private static DaoSession mDaoSession = null;

   public static DaoSession initDB(Context ctx) {
       if (mDaoSession == null) {
           DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ctx, "wmsmp-db", null);
           SQLiteDatabase db = helper.getWritableDatabase();
           DaoMaster daoMaster = new DaoMaster(db);
           daoMaster.newSession();
           mDaoSession = daoMaster.newSession();
       }
       return mDaoSession;
   }
}