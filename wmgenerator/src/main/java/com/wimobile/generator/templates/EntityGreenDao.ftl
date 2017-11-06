package ${packageName};

import com.wimobile.wmcommon.greendao.EntityDB;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
<#if types.associations?has_content==true>import org.greenrobot.greendao.annotation.ToOne;</#if>
<#if types.hasNotNulls()==true>import org.greenrobot.greendao.annotation.NotNull;</#if>
<#if types.hasIndexes()==true>import org.greenrobot.greendao.annotation.Index;</#if>
<#if types.hasDates()==true>import java.util.Date;</#if>

@Entity(<#if types.getKeys()?size gt 1>active = true,
        indexes={
            @Index(
                value = "<#list types.getKeys() as keys>${keys.name?uncap_first}<#sep>, </#list>")<#if indexes?has_content==true>,
            <@provideIndexes/></#if>
}<#elseif indexes?has_content==true && types.getKeys()?size == 1>
        active = true,
        indexes={
            <@provideIndexes/>}<#else>
        active = true
            </#if>)
public class ${className} implements EntityDB{
    @Id
    private Long id;

    <#if notNullTypes?has_content==true>
    @NotNull
    <#list notNullTypes?sort_by("name") as property>
    <@provideDataTypes property=property/>
    </#list>
    </#if>

    <#if nullTypes?has_content==true>
        <#list nullTypes?sort_by("name") as property>
    <@provideDataTypes property=property/>
        </#list>
    </#if>

    <#if types.associations?has_content==true>
    <#list types.associations?sort_by("name") as association>
    @ToOne(joinProperty = "id")
    ${association.getToRole().getType().getClassName()?cap_first?remove_ending("Db")?remove_ending("db")?remove_ending("dB")}DB ${association.getName()?uncap_first}FK;
    </#list>
    </#if>

    @Override
    public Long getId() {
       return id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }
}
<#macro provideDataTypes property>
<#if property.type.className=="int">
    private Integer ${property.getName()?uncap_first};
<#else>
    private ${property.type.className?cap_first} ${property.getName()?uncap_first};
</#if>
</#macro>
<#macro provideIndexes>
<#list indexes?sort_by("name") as property><#if property?has_next==true>
            @Index(
                name = "${property.getIndexName()}",
                value = "${property.getName()?uncap_first} ${property.getIndexOrder()}"<#if property.isUnique()==true>,unique = true),<#else>),</#if>
                <#else>
            @Index(
                name = "${property.getIndexName()}",
                value = "${property.getName()?uncap_first} ${property.getIndexOrder()}"<#if property.isUnique()==true>,unique = true),<#else>)</#if></#if></#list>
</#macro>