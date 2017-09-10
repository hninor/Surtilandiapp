package unal.edu.co.surtilandiapp.core.data.business;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import unal.edu.co.surtilandiapp.core.application.SurtilandiappApplication;
import unal.edu.co.surtilandiapp.core.data.database.greendao.DaoSession;
import unal.edu.co.surtilandiapp.core.data.database.greendao.ListasDB;
import unal.edu.co.surtilandiapp.core.data.database.greendao.ListasDBDao;


/**
 * Created by hnino on 14/07/2017.
 */

public class ListasBusiness {

    private SurtilandiappApplication mApp;
    private DaoSession mDaoSession;

    public ListasBusiness(Context context) {
        mApp = (SurtilandiappApplication) context;
        mDaoSession = mApp.getDaoSession();
    }

    public List<String> consultarLista(String idLista) {
        List<String> respuesta = new ArrayList<>();
        QueryBuilder<ListasDB> queryBuilder = mDaoSession.getListasDBDao().queryBuilder();
        List<ListasDB> listasDBs = queryBuilder.where(ListasDBDao.Properties.IdLista.eq(idLista)).list();
        for(ListasDB item : listasDBs) {
            respuesta.add(item.getValor());
        }
        return respuesta;
    }
}
