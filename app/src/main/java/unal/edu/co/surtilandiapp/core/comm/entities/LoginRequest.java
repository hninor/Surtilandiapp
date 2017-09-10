
package unal.edu.co.surtilandiapp.core.comm.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("AppVersion")
    @Expose
    private String appVersion;
    @SerializedName("Imei")
    @Expose
    private String imei;
    @SerializedName("KeyType")
    @Expose
    private Integer keyType;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("PushNotificationKey")
    @Expose
    private String pushNotificationKey;
    @SerializedName("Serial")
    @Expose
    private String serial;
    @SerializedName("UserName")
    @Expose
    private String userName;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getKeyType() {
        return keyType;
    }

    public void setKeyType(Integer keyType) {
        this.keyType = keyType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPushNotificationKey() {
        return pushNotificationKey;
    }

    public void setPushNotificationKey(String pushNotificationKey) {
        this.pushNotificationKey = pushNotificationKey;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
