/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class OAuthResponse implements Serializable {

    @SerializedName(value = "access_token")
    private String accessToken;
    @SerializedName(value = "scope")
    private String scope;
    @SerializedName(value = "user")
    private User user;
    @SerializedName(value = "context")
    private String storeHash;

    public int getId() {
        return user.getId();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getUserName() {
        return user.getUsername();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getStoreHash() {
        return storeHash;
    }

    public void setStoreHash(String storeHash) {
        this.storeHash = storeHash;
    }

    public boolean isValid() {
        return (storeHash != null && !storeHash.isEmpty() && accessToken != null && !accessToken.isEmpty());
    }

    /**
     *
     */
    class User {

        @SerializedName(value = "id")
        private int id;
        @SerializedName(value = "username")
        private String username;
        @SerializedName(value = "email")
        private String email;

        public String getEmail() {
            return email;
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }
    }
}
