/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class Credentials {

    private String clientId;
    private String clientSecret;
    private String userName;
    private String apiToken;
    private String storeHash;
    private String accessToken;

    private Credentials() {
    }

    public String getStoreHash() {
        return storeHash;
    }

    private Credentials(String userName, String storeHash, String apiToken) {
        this.userName = userName;
        this.storeHash = storeHash;
        this.apiToken = apiToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getUserName() {
        return userName;
    }

    public boolean hasAccessToken() {
        return accessToken != null && accessToken.length() != 0;
    }

    public String getBasicAuth() {
        String auth = userName + ":" + apiToken;
        return "Basic " + Base64.encodeBase64String(auth.getBytes());
    }

    public static Credentials create(String userName, String storeHash, String apiToken) {
        return new Credentials(userName, storeHash, apiToken);
    }

    public static Credentials createWithToken(String clientId, String hash, String token) {
        Credentials cred = new Credentials();
        cred.clientId = clientId;
        cred.storeHash = hash;
        cred.accessToken = token;
        return cred;
    }
}