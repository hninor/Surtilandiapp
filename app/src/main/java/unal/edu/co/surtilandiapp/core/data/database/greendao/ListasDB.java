package unal.edu.co.surtilandiapp.core.data.database.greendao;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;


@Entity(
        active = true
            )
public class ListasDB {
    @Id
    private Long id;

    @NotNull
    private Integer idListas;

    private String idLista;
    private String valor;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1015909165)
    private transient ListasDBDao myDao;


    @Generated(hash = 1279428325)
    public ListasDB(Long id, @NotNull Integer idListas, String idLista,
                    String valor) {
        this.id = id;
        this.idListas = idListas;
        this.idLista = idLista;
        this.valor = valor;
    }

    @Generated(hash = 1820867097)
    public ListasDB() {
    }


    public Integer getIdListas() {
        return this.idListas;
    }

    public void setIdListas(Integer idListas) {
        this.idListas = idListas;
    }

    public String getIdLista() {
        return this.idLista;
    }

    public void setIdLista(String idLista) {
        this.idLista = idLista;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 549188172)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getListasDBDao() : null;
    }
}
