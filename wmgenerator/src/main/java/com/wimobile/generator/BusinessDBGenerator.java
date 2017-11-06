package com.wimobile.generator;

import org.restlet.ext.odata.internal.edm.AssociationEnd;
import org.restlet.ext.odata.internal.edm.EntityType;
import org.restlet.ext.odata.internal.edm.NavigationProperty;
import org.restlet.ext.odata.internal.edm.Property;
import org.restlet.ext.odata.internal.edm.Type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class BusinessDBGenerator {

    public static void main(String[] args) throws Exception {

        System.out.println("Iniciando generador");
        org.apache.log4j.BasicConfigurator.configure();
        TemplateHandler tmpHandler = new TemplateHandler();
        tmpHandler.readFile();
    }

    public static class TemplateHandler {

        public static final String TOKEN = "`";

        public void readFile() {

            BufferedReader br = null;
            try {

                String sCurrentLine;

                br = new BufferedReader(new FileReader(GenConfiguration.BUSSINESS_DB));
                String token = "`";
                ArrayList<Property> properties = new ArrayList<>();
                String currentTableName;
                EntityType entityType = null;
                boolean begintable = false;
                boolean inconstraint = false;

                ArrayList<EntityType> entityTypes = new ArrayList<>();
                HashMap<String, Property> hashProperties = new HashMap<>();
                List<NavigationProperty> listNavProperties = new ArrayList<>();

                while ((sCurrentLine = br.readLine()) != null) {

                    if(sCurrentLine.equalsIgnoreCase("") ||
                            (sCurrentLine.charAt(0)=='-' && sCurrentLine.charAt(1)=='-')
                            || sCurrentLine.contains("SET @OLD_")){
                        continue;
                    }

                    if (sCurrentLine.startsWith("CREATE")) {
                        currentTableName = extractEntity(sCurrentLine);
                        if (currentTableName != null) {
                            System.out.println("Table:" + currentTableName);
                            begintable = true;
                            entityType = new EntityType(currentTableName);
                            properties = new ArrayList<>();
                            hashProperties = new HashMap<>();
                        }
                    } else if (sCurrentLine.contains("PRIMARY")) {
                        if (entityType != null) {
                            ArrayList<String> entities = extractEntityList(sCurrentLine);
                            List<Property> keys = new ArrayList<>();
                            for (String entityName: entities) {
                                keys.add(hashProperties.get(entityName));
                            }
                            entityType.setKeys(keys);
                            if(!entityType.hasIndexes() && entities.size()>1){
                                entityType.setHasIndexes(true);
                            }
                        }

                    } else if (sCurrentLine.contains("INDEX")) {
                        List<String> indexProperties = extractIndexProperties(sCurrentLine);
                        boolean uniqueFlag=false;
                        if(sCurrentLine.contains("UNIQUE")){
                            uniqueFlag=true;
                        }
                        int count=0;

                        idxProperty:
                        for(Property prop: properties){
                            if(prop.getPropertyName().equalsIgnoreCase(indexProperties.get(1))){
                                properties.get(count).setHasIndex(true);
                                properties.get(count).setIndexName(indexProperties.get(0));
                                properties.get(count).setIndexOrder(indexProperties.get(2));
                                if(uniqueFlag){
                                    properties.get(count).setUnique(true);
                                }else{
                                    properties.get(count).setUnique(false);
                                }
                                break idxProperty;
                            }
                            count++;
                        }
                        if(!entityType.hasIndexes()){
                            entityType.setHasIndexes(true);
                        }
                    } else if (sCurrentLine.contains("CONSTRAINT")) {
                        inconstraint = true;
                        ArrayList<String> stringArrayList = new ArrayList<>();
                        int i = 0;
                        while ((sCurrentLine = br.readLine()) != null && i <2) {
                            stringArrayList.add(sCurrentLine);
                            i++;

                        }

                        NavigationProperty navProperty = getNavigationFromSql(stringArrayList);
                        if ( navProperty != null) {
                            listNavProperties.add(navProperty);
                        }
                    } else {
                        if (begintable && !inconstraint) {
                            Property row = getRowFromSql(sCurrentLine);
                            if (row != null) {
                                if(!row.isNullable() && !entityType.hasNotNulls()){
                                    entityType.setHasNotNulls(true);
                                }
                                if(row.getType().getName().equalsIgnoreCase("DateTime") && !entityType.hasDates()){
                                    entityType.setHasDates(true);
                                }
                                properties.add(row);
                                hashProperties.put(row.getName(), row);
                            }
                        }
                    }
                    if (sCurrentLine.contains(";")) {
                        if (entityType != null) {
                            entityType.setProperties(properties);
                            if (!listNavProperties.isEmpty()) {
                                entityType.setAssociations(listNavProperties);
                            }
                            entityTypes.add(entityType);
                            entityType = null;
                        }
                        begintable = false;
                        inconstraint = false;
                        listNavProperties = new ArrayList<>();
                    }
                }
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);

                cfg.setDirectoryForTemplateLoading(new File(GenConfiguration.GENBASE));
                cfg.setDefaultEncoding("UTF-8");
                cfg.setLocale(Locale.US);
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

                String packageName;

                String className = "CustomGenerator";
                packageName = GenConfiguration.PKG_BUSINESS_DB;

                Map<String, Object> dataModel = new HashMap<>();
                dataModel.put("types", entityTypes);
                dataModel.put("className", className);
                dataModel.put("packageName", packageName);
                dataModel.put("targetPackageName", GenConfiguration.PKG_BUSINESS_DB + ".greendao");
                dataModel.put("packageNameCommon", GenConfiguration.PKG_COMMON);
                dataModel.put("targetDir", GenConfiguration.DIR_CREATOR_BUSINESS_DB);

                for (EntityType entity : entityTypes) {
                    className = entity.getClassName()+"DB";
                    dataModel.put("className", className);
                    dataModel.put("types", entity);
                    dataModel.put("notNullTypes", getNotNullTypes(entity.getProperties()));
                    dataModel.put("nullTypes", getNullTypes(entity.getProperties()));
                    dataModel.put("indexes", getIndexesProperties(entity.getProperties()));
                    dataModel.put("packageName", packageName+".greendao");
                    dataModel.put("package", GenConfiguration.PKG_COMMON_DB);
                    Utils.buildFile(GenConfiguration.DIR_CREATOR_BUSINESS_DB, cfg, dataModel,
                            "EntityGreenDao.ftl", packageName+".greendao", className);
                }

                packageName = GenConfiguration.PKG_BUSINESS_DB;
                className = "MainMapper";
                dataModel.put("className", className);
                dataModel.put("types", entityTypes);
                dataModel.put("packageName", packageName);
                Utils.buildFile(GenConfiguration.DIR_CREATOR_BUSINESS_DB, cfg, dataModel,
                        "TemplateMainMapper.ftl", packageName+".mapper", className);

                className = "QueueManagerRest";
                dataModel.put("className", className);
                dataModel.put("types", entityTypes);
                Utils.buildFile(GenConfiguration.DIR_CREATOR_BUSINESS_DB, cfg, dataModel,
                        "QueueManagerRest.ftl", packageName + ".mapper", className);

                className = "ConverterData";
                dataModel.put("className", className);
                Utils.buildFile(GenConfiguration.DIR_CREATOR_BUSINESS_DB, cfg, dataModel,
                        "ConverterData.ftl", packageName + ".mapper", className);

                className = "SyncManager";
                dataModel.put("className", className);
                Utils.buildFile(GenConfiguration.DIR_CREATOR_BUSINESS_DB, cfg, dataModel,
                        "TemplateSyncManager.ftl", packageName + ".mapper", className);

                for (EntityType entity : entityTypes) {
                    className = entity.getClassName();
                    dataModel = new HashMap<>();
                    dataModel.put("entity", entity);
                    dataModel.put("className", className);
                    dataModel.put("packageName", packageName);
                    dataModel.put("packageNameCommon", GenConfiguration.PKG_COMMON);
                    Utils.buildFile(GenConfiguration.DIR_CREATOR_BUSINESS_DB, cfg, dataModel,
                            "TemplateEntityMapper.ftl", packageName + ".mapper",
                            className + "Mapper");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private Property getRowFromSql(String sCurrentLine) {

            String rowName = extractEntity(sCurrentLine);
            if (rowName != null) {
                String typename = "UNKNOWN";
                if (sCurrentLine.contains("VARCHAR") || sCurrentLine.contains("TEXT")) {
                    typename = "String";
                } else if (sCurrentLine.contains("BIGINT")) {
                    typename = "Int64";
                } else if (sCurrentLine.contains("BOOL")
                        || sCurrentLine.contains("BOOLEAN")
                        || sCurrentLine.contains("TINYINT(1)")) {
                    typename = "Boolean";
                } else if (sCurrentLine.contains("SMALLINT")
                        || sCurrentLine.contains("TINYINT")
                        ) {
                    typename = "Int16";
                } else if (sCurrentLine.contains("INT")
                        || sCurrentLine.contains("INTEGER")) {
                    typename = "Int32";
                } else if (sCurrentLine.contains("REAL")
                        || sCurrentLine.contains("DOUBLE")) {
                    typename = "Double";
                } else if (sCurrentLine.contains("DATETIME")) {
                    typename = "DateTime";
                } else if (sCurrentLine.contains("DECIMAL")
                        || sCurrentLine.contains("DEC")
                        || sCurrentLine.contains("FIXED")) {
                    typename = "Decimal";
                }
                Property row = new Property(rowName);
                if(sCurrentLine.contains("NOT NULL")){
                    row.setNullable(false);
                }else{
                    row.setNullable(true);
                }
                Type type = new Type(typename);
                row.setType(type);
                System.out.println("row:" + rowName + " type:" + type.getName());
                return row;
            }
            return null;
        }

        private List<Property> getNullTypes(List<Property> properties) {
            List<Property> nullProperties=new ArrayList<>();
            for (Property prop : properties) {
                if (prop.isNullable()){
                    nullProperties.add(prop);
                }
            }
            return nullProperties;
        }

        private List<Property> getNotNullTypes(List<Property> properties) {
            List<Property> notNullProperties=new ArrayList<>();
            for (Property prop : properties) {
                if (!prop.isNullable()){
                    notNullProperties.add(prop);
                }
            }
            return notNullProperties;
        }

        private List<Property> getIndexesProperties(List<Property> properties) {
            List<Property> propertiesWithIndex=new ArrayList<>();
            for (Property prop : properties) {
                if (prop.hasIndex()){
                    propertiesWithIndex.add(prop);
                }
            }
            return propertiesWithIndex;
        }

        public String extractEntity(String sCurrentLine) {
            int beginIndex = sCurrentLine.indexOf(TOKEN);
            if (beginIndex != -1) {
                int endIndex = sCurrentLine.indexOf(TOKEN, beginIndex + 1);
                if (endIndex != -1) {
                    return sCurrentLine.substring(beginIndex + 1, endIndex);
                }
            }
            return null;
        }

        public List<String> extractIndexProperties(String sCurrentLine) {
            List<String> answer=new ArrayList<>();
            for(String word:sCurrentLine.replace(TOKEN,"").
                    replace("(","").replace(")","").replace(",","").replace(";","").split(" ")){
                if(word.equalsIgnoreCase("UNIQUE")||word.equalsIgnoreCase("INDEX")||word.equalsIgnoreCase("")){

                }else{
                    answer.add(word);
                }
            }
            return answer;
        }

        private ArrayList<String> extractEntityList(String inputLine) {

            ArrayList<String> entities = new ArrayList<>();
            String sCurrentLine = inputLine;
            int beginIndex = sCurrentLine.indexOf(TOKEN);
            while (beginIndex != -1) {
                int endIndex = sCurrentLine.indexOf(TOKEN, beginIndex + 1);
                if (endIndex != -1) {
                    entities.add(sCurrentLine.substring(beginIndex + 1, endIndex));
                }
                sCurrentLine = sCurrentLine.substring(endIndex +1);
                beginIndex = sCurrentLine.indexOf(TOKEN);
            }
            return entities;
        }
        private NavigationProperty getNavigationFromSql(ArrayList<String> stringArrayList) {
            System.out.println("Size "+stringArrayList.size());
            if (stringArrayList.size() < 2) {
                return null;
            }
            for (String s: stringArrayList){
                System.out.println("Array "+s);
            }

            String fkName = extractEntity(stringArrayList.get(0));
            String entity = extractEntity(stringArrayList.get(1));

            NavigationProperty navigationProperty = new NavigationProperty(fkName);
            AssociationEnd associationEnd= new AssociationEnd(fkName+"associationEnd");
            associationEnd.setMultiplicity("1");
            associationEnd.setType(new EntityType(entity+"DB"));

            navigationProperty.setToRole(associationEnd);

            return navigationProperty;
        }
    }
}