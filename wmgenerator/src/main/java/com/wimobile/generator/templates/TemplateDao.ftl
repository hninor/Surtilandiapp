package ${packageName};

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property.PropertyBuilder;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.ToMany;
import de.greenrobot.daogenerator.Index;
import com.wimobile.generator.GenConfiguration;

public class ${className} {

    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "${targetPackageName}");
        
        Index index;
        <#list types as type>
        <#assign classname=type.getClassName()>
        
        Entity ${classname?lower_case}  = schema.addEntity("${classname}DB");
        ${classname?lower_case}.implementsInterface(GenConfiguration.PKG_COMMON_DB + ".EntityDB");
        Property id${classname}Property = ${classname?lower_case}.addIdProperty().autoincrement().getProperty();
        <#list type.properties?sort_by("name") as property>
          <#if property.type??>
        PropertyBuilder <@prefixByNameLower property=property />${classname}PropertyBuilder = ${classname?lower_case}.add${property.type.className?cap_first}Property("<@prefixByNameLower property=property />");
          <#else>
        // private [error: no defined type] PropertyBuilder <@prefixByNameLower property=property />${classname}PropertyBuilder = ${classname?lower_case}.add${property.type.className?cap_first}Property("<@prefixByNameLower property=property />");
          </#if>
        </#list>
        
        <#if type.keys??>      
            <#if type.keys?size == 1 >
            <#list type.keys as key>
        ${key.normalizedName}${classname}PropertyBuilder.index();
        ${key.normalizedName}${classname}PropertyBuilder.unique();
            </#list>
            <#else>
        index = new Index();
            <#list type.keys as key>
        index.addProperty(${key.normalizedName}${classname}PropertyBuilder.getProperty());
            </#list>
        ${classname?lower_case}.addIndex(index);
            </#if>
        </#if>
        </#list>
        
        
        
        <#list types as type>
        <#assign classname=type.getClassName()>        
        <#list type.associations?sort_by("name") as association>
            <#if !association.toRole.toMany>
         Property id${association.toRole.type.className}${classname}Property = ${classname?lower_case}.addLongProperty("id${association.toRole.type.className}Movil").getProperty();
            </#if>
            
        </#list>
        </#list>
        <#list types as type>
        <#assign classname=type.getClassName()>        
        <#list type.associations?sort_by("name") as association>
              <#if association.toRole.toMany>
        ToMany ${classname?lower_case}To${association.toRole.type.className}s = ${classname?lower_case}.addToMany(${association.toRole.type.className?lower_case}, id${classname}${association.toRole.type.className}Property);
        ${classname?lower_case}To${association.toRole.type.className}s.setName("${association.toRole.type.className?lower_case}s"); 
            <#else>
         ${classname?lower_case}.addToOne(${association.toRole.type.className?lower_case}, id${association.toRole.type.className}${classname}Property, "${association.toRole.type.className?lower_case}");
            </#if>
            
        </#list>
        </#list>
        new DaoGenerator().generateAll(schema, "${targetDir}");
    }
}
<#macro prefixByNameLower property>
<#if property.propertyName?cap_first = "Id">
customid
<#else>
${property.propertyName}</#if></#macro> 