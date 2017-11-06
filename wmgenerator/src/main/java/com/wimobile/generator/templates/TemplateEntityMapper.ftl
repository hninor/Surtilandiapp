package ${packageName}.mapper;

import java.io.IOException;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.fasterxml.jackson.core.JsonToken;
import ${packageName}.greendao.${className}DB;

import ${packageNameCommon}.model.ConverterData;
import ${packageNameCommon}.util.JsonUtil;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

<#assign type=entity>

public class ${className}Mapper implements ConverterData<${className}DB>{

    public static String QUERY_FILTER = "/${entity.getName()}";     

    @Override
    public ${className}DB findByKeys(${className}DB cu, AbstractDao<${className}DB, Long> entityDao) {
        <#assign openAND=0>
        QueryBuilder<${className}DB> qbEnt = entityDao.queryBuilder();
        <#assign countKeys=type.getKeys()?size>
        <#if countKeys lte 2>
        ${className}DB db = (${type.getClassName()}DB) qbEnt
            .where(<#list type.keys as key>${packageName}.greendao
            .${type.getClassName()}DBDao.Properties.${key.getName()?cap_first}
            .eq(cu.get${key.getName()?cap_first}())<#if key_has_next>,</#if>
            </#list>).unique();
        <#else>
        ${className}DB db = (${type.getClassName()}DB) qbEnt
            .where(<#list type.keys as key>${packageName}.greendao
            .${type.getClassName()}DBDao.Properties.${key.getName()?cap_first}
            .eq(cu.get${key.getName()?cap_first}())<#if key_has_next><#if openAND == 0>,
            qbEnt.and(<#assign openAND=1><#else>
            ,</#if></#if></#list><#if openAND == 1>)</#if>).unique();
        </#if>
        if (db != null) {
            cu.setId(db.getId());
        }
        return cu;

    }
    
    public ${className}DB jsonToDb(JsonParser jp) {

        ${className}DB db = new ${className}DB();

        try {
            <#list type.properties?sort_by("name") as property>
            jp.nextToken();
            jp.nextToken();
            db.set${property.getName()?cap_first}(jp.getValueAsString() != null?<@getDataByTypeJson property=property/>:null);
             </#list>
             if (jp.nextToken() == JsonToken.END_OBJECT) {
                return db;
            }
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
    
    @Override
    public ${className}DB arrayToDb(String[] record) {
        ${className}DB db = null;
    
        try {
            int i = 0;
            db = new ${className}DB();
            <#list type.properties as property>
            db.set${property.getName()?cap_first}(<@getDataByType property=property value="record[i++].trim()"/>);
             </#list>
         } catch (Exception e) {
            Log.d("${className}", "${className} arrayToBD Error Message: " + e.getMessage());
        }
        
        return db;
    }

    public static JsonElement toJsonElement(${className}DB db){
        JsonElement jsonElement = new JsonObject();
        try {
            <#list type.properties as property>
            jsonElement.getAsJsonObject().addProperty("${property.propertyName?cap_first}", JsonUtil.getJsonString(db.get${property.getName()?cap_first}()));
            </#list>
        } catch (Exception e) {
            //TODO: LOG
        }
        return jsonElement;
    }
}
<#macro toUnderScore camelCase>
${camelCase?replace("[A-Z]", "_$0", 'r')?upper_case}</#macro> 

<#macro prefixByName property>
<#if property.propertyName?cap_first = "Id">
CustomId
<#else>
${property.propertyName?cap_first}</#if></#macro> 

<#compress>
<#macro getDataByType property value>
    <#if property.type.className?cap_first = "String">
        <#t>${value}<#t>
        <#else>
        <#if property.type.className?cap_first = "Date">
            <#t>JsonUtil.getDateFromJson(record[i++].trim()+"z")<#t>
            <#else>
            <#if property.type.className?cap_first = "Int">
                <#t>Integer.valueOf(${value})<#t>
                <#else>
                <#t>${property.type.className?cap_first}.valueOf(${value})<#t>
            </#if>
        </#if>
    </#if>
</#macro>
</#compress>
<#compress>
<#macro getDataByTypeJson property>
    <#if property.type.className?cap_first = "Date">
        <#t>JsonUtil.getDateFromJson(jp.getValueAsString())<#t>
        <#else>
        <#if property.type.className?cap_first = "Short">
            <#t>(short)jp.getValueAsInt()<#t>
            <#else>
            <#if property.type.className?cap_first = "Byte">
                <#t>(byte)jp.getValueAsInt()<#t>
                <#else>
                <#t>jp.getValueAs${property.type.className?cap_first}()<#t>
            </#if>
        </#if>
    </#if>
</#macro>
</#compress>