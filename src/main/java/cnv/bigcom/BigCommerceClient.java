/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom;

import cnv.bigcom.services.BaseService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Scanner;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class BigCommerceClient {

    private Credentials creds;
    String baseUrl;
    private boolean debug = false;

    public void enableDebug() {
        debug = true;
    }

    public void disableDebug() {
        debug = false;
    }

    public boolean isDebugEnabled() {
        return debug;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public BigCommerceClient(Credentials creds) {
        this.creds = creds;
        if (creds.hasAccessToken()) {
            this.baseUrl = "https://api.bigcommerce.com/stores/" + creds.getStoreHash() + "/v2";
        } else {
            this.baseUrl = "https://store-" + creds.getStoreHash() + ".mybigcommerce.com/api/v2";
        }

    }

    public Credentials getCredentials() {
        return creds;
    }

    /**
     * This method will create the Given Service
     *
     * @param <T>
     * @param cls Service Class
     * @return the Service Object
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T extends BaseService> T getService(Class<T> cls) throws InstantiationException, IllegalAccessException {
        T obj = cls.newInstance();
        obj.setClient(this);
        return obj;
    }

    /**
     * This method will request for the access token for the given shop
     *
     * @param shopName shop name
     * @param clientId client app id
     * @param clientSecret client app secret
     * @param code one time access code
     * @return access token
     */
    public static String requestAccessToken(String clientId, String clientSecret,
            String code, String scope, String grantType, String redirectUri, String context) {
        String accessToken = null;
        try {
            String url = "https://login.bigcommerce.com/oauth2/token";
            HttpUriRequest tokenRequest = RequestBuilder.post().
                    setUri(url).
                    addParameter("client_id", clientId).
                    addParameter("client_secret", clientSecret).
                    addParameter("code", code).
                    addParameter("scope", scope).
                    addParameter("grant_type", grantType).
                    addParameter("redirect_uri", redirectUri).
                    addParameter("context", "stores/" + context).
                    build();
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse tokenRes = httpClient.execute(tokenRequest);
            Scanner scanner = new Scanner(tokenRes.getEntity().getContent());
            StringBuilder buff = new StringBuilder();
            while (scanner.hasNext()) {
                buff.append(scanner.nextLine());
            }
            scanner.close();

            Gson gson = new Gson();
            HashMap tokenObj = gson.fromJson(buff.toString(), HashMap.class);
            System.out.println(tokenObj);
            accessToken = (String) tokenObj.get("access_token");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
        }
        return accessToken;
    }
}
