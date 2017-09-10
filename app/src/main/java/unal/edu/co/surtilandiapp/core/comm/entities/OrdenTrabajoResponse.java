
package unal.edu.co.surtilandiapp.core.comm.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrdenTrabajoResponse {

    @SerializedName("CodigoResultado")
    @Expose
    private String codigoResultado;
    @SerializedName("Direccion")
    @Expose
    private String direccion;
    @SerializedName("IdEvento")
    @Expose
    private int idEvento;
    @SerializedName("IdOrden")
    @Expose
    private int idOrden;
    @SerializedName("IdSuscriptor")
    @Expose
    private String idSuscriptor;
    @SerializedName("IdTipoDeOrden")
    @Expose
    private int idTipoDeOrden;
    @SerializedName("Latitud")
    @Expose
    private float latitud;
    @SerializedName("Localizacion")
    @Expose
    private String localizacion;
    @SerializedName("Longitud")
    @Expose
    private float longitud;
    @SerializedName("Municipio")
    @Expose
    private String municipio;
    @SerializedName("NombreTitular")
    @Expose
    private String nombreTitular;
    @SerializedName("NumeroOrden")
    @Expose
    private String numeroOrden;
    @SerializedName("Objetivo")
    @Expose
    private String objetivo;
    @SerializedName("Observacion")
    @Expose
    private String observacion;
    @SerializedName("Prioridad")
    @Expose
    private String prioridad;
    @SerializedName("P\u00e1gina")
    @Expose
    private int pGina;
    @SerializedName("Ruta")
    @Expose
    private String ruta;
    @SerializedName("Secuencia")
    @Expose
    private int secuencia;
    @SerializedName("Tama\u00f1o")
    @Expose
    private int tamaO;
    @SerializedName("Telefono")
    @Expose
    private String telefono;

    public String getCodigoResultado() {
        return codigoResultado;
    }

    public void setCodigoResultado(String codigoResultado) {
        this.codigoResultado = codigoResultado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getIdSuscriptor() {
        return idSuscriptor;
    }

    public void setIdSuscriptor(String idSuscriptor) {
        this.idSuscriptor = idSuscriptor;
    }

    public int getIdTipoDeOrden() {
        return idTipoDeOrden;
    }

    public void setIdTipoDeOrden(int idTipoDeOrden) {
        this.idTipoDeOrden = idTipoDeOrden;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public int getPGina() {
        return pGina;
    }

    public void setPGina(int pGina) {
        this.pGina = pGina;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public int getTamaO() {
        return tamaO;
    }

    public void setTamaO(int tamaO) {
        this.tamaO = tamaO;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
