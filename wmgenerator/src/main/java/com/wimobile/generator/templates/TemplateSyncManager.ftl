package ${packageName}.mapper;

import java.io.IOException;

import android.content.Context;
import android.util.Log;


import ${packageNameCommon}.communication.BaseFilter;
import ${packageNameCommon}.communication.SyncException;
import ${packageNameCommon}.model.BaseProcessor;
<#list types as type>
import ${packageName}.greendao.${type.getClassName()}DB;
 </#list>  
 
import ${packageName}.greendao.DaoSession;


public class ${className} {

    protected DaoSession daoSession;
    private static final String LOGTAG = "SmpSync";
    protected Context context;
    
    public SyncManager(Context ctx) {
        this.daoSession = MainMapper.initDB(ctx);
        this.context = ctx;
    }


    public void syncAll(BaseFilter baseFilter) throws SyncException, IOException {
        <#list types as type>
        sync${type.getClassName()}(baseFilter);
         </#list>
    }
    <#list types as type>
    <#if type.keys??>
    public int sync${type.getClassName()}(BaseFilter baseFilter) throws IOException, SyncException{

        if (baseFilter != null) {
            if (baseFilter.getBaseProcessor() == null) {
                baseFilter.setBaseProcessor(
                        new BaseProcessor<${type.getClassName()}DB>(daoSession.get${type.getClassName()}DBDao(),
                                new ${type.getClassName()}Mapper(), context));
            }
            baseFilter.operateEntity(BaseProcessor.OperationType.INSERT);
            return baseFilter.getBaseProcessor().getTotal();
        }
        return 0;
    }

    </#if>
    </#list>
    
    public static class SyncException extends Exception {
        
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public SyncException(String errorMessage) {
            super(errorMessage);
        }

        public SyncException(Throwable throwable) {
            super(throwable);
        }
    }
}
