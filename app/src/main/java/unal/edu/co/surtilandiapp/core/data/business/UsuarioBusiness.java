package unal.edu.co.surtilandiapp.core.data.business;

import android.content.Context;

import unal.edu.co.surtilandiapp.core.application.SurtilandiappApplication;
import unal.edu.co.surtilandiapp.core.data.database.greendao.DaoSession;
import unal.edu.co.surtilandiapp.core.data.database.greendao.UsuarioDB;


/**
 * Created by hnino on 14/07/2017.
 */

public class UsuarioBusiness {

    private SurtilandiappApplication mApp;
    private DaoSession mDaoSession;

    public UsuarioBusiness(Context context) {
        mApp = (SurtilandiappApplication) context;
        mDaoSession = mApp.getDaoSession();
    }

    public void guardarUsuario(String username, String token) {
        mDaoSession.getUsuarioDBDao().deleteAll();
        UsuarioDB usuarioDB = new UsuarioDB();
        usuarioDB.setUsername(username);
        usuarioDB.setToken(token);
        mDaoSession.getUsuarioDBDao().insert(usuarioDB);
    }

    public UsuarioDB obtenerUsuario() {
        return mDaoSession.getUsuarioDBDao().queryBuilder().unique();
    }



}
