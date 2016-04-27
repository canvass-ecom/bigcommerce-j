/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class SignedPayload {

    @SerializedName(value = "access_token")
    private String accessToken;
    @SerializedName(value = "scope")
    private String scope;
    @SerializedName(value = "timestamp")
    private float timestamp;
    @SerializedName(value = "user")
    private User user;
    @SerializedName(value = "store_hash")
    private String storeHash;
    @SerializedName(value = "context")
    private String context;
    private String hmacSignature;
    private String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getHmacSignature() {
        return hmacSignature;
    }

    public void setHmacSignature(String hmacSignature) {
        this.hmacSignature = hmacSignature;
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

    /**
     *
     */
    class User {

        @SerializedName(value = "id")
        private int id;
        @SerializedName(value = "email")
        private String email;

        public String getEmail() {
            return email;
        }

        public int getId() {
            return id;
        }
    }
}
