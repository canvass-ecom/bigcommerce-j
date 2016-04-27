/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom;

import cnv.bigcom.model.OAuthResponse;
import cnv.bigcom.model.PingModel;
import cnv.bigcom.model.SignedPayload;
import cnv.bigcom.services.BaseService;
import cnv.bigcom.services.PingService;
import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.util.Scanner;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;
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

    public BigCommerceClient(Credentials creds) throws InstantiationException, IllegalAccessException, IOException {
        this.creds = creds;
        if (creds.hasAccessToken()) {
            this.baseUrl = "https://api.bigcommerce.com/stores/" + creds.getStoreHash() + "/v2";
        } else {
            this.baseUrl = getStoreUrl(creds.getStoreHash()) + "/api/v2";
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
    public final <T extends BaseService> T getService(Class<T> cls) throws InstantiationException, IllegalAccessException {
        T obj = cls.newInstance();
        obj.setClient(this);
        return obj;
    }

    public final void ping() throws InstantiationException, IllegalAccessException, IOException {
        PingModel ping = getService(PingService.class).ping();
        System.out.println(new Gson().toJson(ping));
        if (ping != null && ping.isValidObj()) {
            System.out.println("Connected to BigCommerce at: " + ping.getTime());
        } else {
            System.out.println("Unable to connect to BigCommerce reason: " + ping != null ? ping.getError() : "Not found");
        }
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
    public static OAuthResponse requestAccessToken(String clientId, String clientSecret,
            String code, String scope, String redirectUri, String context) {
        return requestAccessToken(clientId, clientSecret, code, scope, "authorization_code", redirectUri, context);
    }

    /**
     *
     * @param clientId
     * @param clientSecret
     * @param code
     * @param scope
     * @param grantType
     * @param redirectUri
     * @param context
     * @return
     */
    public static OAuthResponse requestAccessToken(String clientId, String clientSecret,
            String code, String scope, String grantType, String redirectUri, String context) {
        OAuthResponse accessToken = null;
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
            return ResponseParser.parser().parse(buff.toString(), OAuthResponse.class);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
        }
        return accessToken;
    }

    /**
     *
     * @param signedPayload
     * @return
     */
    public static SignedPayload parsePayload(String signedPayload) {
        String[] split = signedPayload.split("\\.");
        String json = new String(Base64.decode(split[0]));
        String hmac_sig = new String(Base64.decode(split[1]));
        SignedPayload payloadObj = Utill.getGson().fromJson(json, SignedPayload.class);
        payloadObj.setJson(json);
        payloadObj.setHmacSignature(hmac_sig);
        return payloadObj;
    }

    /**
     *
     */
    public static boolean isValidPayload(SignedPayload payload, String secret) {
        boolean flag = false;
        // Now hashing with sha256 algorithem
        String hmac = payload.getHmacSignature();
        try {
            String algorithm = "HmacSHA256";
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(secret.getBytes(), algorithm));
            byte[] digest = mac.doFinal(payload.getJson().getBytes());
            //System.out.println("Generated Hash: " + Hex.encodeHexString(digest));
            //System.out.println("Original Hash: " + hmac);
            flag = hmac.equals(Hex.encodeHexString(digest).toString());
        } catch (Exception e) {
            System.out.println("Error while checking the request validation:" + e);
            e.printStackTrace(System.out);
        }
        return flag;
    }

    public static String getStoreUrl(String storeHash) {
        return "https://store-" + storeHash + ".mybigcommerce.com";
    }
}
