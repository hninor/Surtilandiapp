

package unal.edu.co.surtilandiapp.core.comm.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("Session")
    @Expose
    private String session;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("TotalAttempts")
    @Expose
    private int totalAttempts;

    /**
     * @return The session
     */
    public String getSession() {
        return session;
    }

    /**
     * @param session The Session
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The Status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The totalAttempts
     */
    public int getTotalAttempts() {
        return totalAttempts;
    }

    /**
     * @param totalAttempts The TotalAttempts
     */
    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

}