package ${packageName}.mapper;

import com.fasterxml.jackson.core.JsonParser;

import org.greenrobot.greendao.AbstractDao;

public interface ConverterData<T> {
	
	public T jsonToDb(JsonParser jp);

	public T arrayToDb(String[] record);

	public T findByKeys(T cu, AbstractDao<T, Long> entityDao);
}