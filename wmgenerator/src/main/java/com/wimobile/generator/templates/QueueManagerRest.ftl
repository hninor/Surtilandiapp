package ${packageName}.mapper;

import android.content.Context;

import com.google.gson.JsonElement;
<#list types as type>
import ${packageName}.greendao.${type.getClassName()}DB;
 </#list>
import ${packageNameCommon}.communication.QueueManager;

/**
 * Created by spinto on 13/09/2016.
 */
public abstract class QueueManagerRest extends QueueManager {

    public QueueManagerRest(Context context) {
        super(context);
    }

    <#list types as type>
    public void addToQueue(${type.getClassName()}DB db){
        String jsonDataStr = entityToString(db);
        if (jsonDataStr != null) {
            QueueManager.storeQueue(jsonDataStr, db.getId(),
            daoSession, MainMapper.${type.getClassName()?upper_case}_CLASS);
        }
    }
    </#list>


    <#list types as type>
    protected String entityToString(${type.getClassName()}DB db) {
        JsonElement jsonElement = ${type.getClassName()}Mapper.toJsonElement(db);
        return jsonElementToString(jsonElement);
    }
    </#list>

}
