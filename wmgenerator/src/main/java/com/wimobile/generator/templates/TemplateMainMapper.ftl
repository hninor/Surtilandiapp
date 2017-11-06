package ${packageName}.mapper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ${packageName}.greendao.DaoMaster;
import ${packageName}.greendao.DaoMaster.DevOpenHelper;
import ${packageName}.greendao.DaoSession;

public class ${className} {
    <#assign countVal=1>
    <#list types as type>
      public static final short ${type.getClassName()?upper_case}_CLASS = ${countVal};
      <#assign countVal=countVal+1>
    </#list>  

   private static DaoSession mDaoSession = null;

   public static DaoSession initDB(Context ctx) {
       if (mDaoSession == null) {
           DevOpenHelper helper = new DaoMaster.DevOpenHelper(ctx, "wmsmp-db", null);
           SQLiteDatabase db = helper.getWritableDatabase();
           DaoMaster daoMaster = new DaoMaster(db);
           daoMaster.newSession();
           mDaoSession = daoMaster.newSession();
       }
       return mDaoSession;
   }
}