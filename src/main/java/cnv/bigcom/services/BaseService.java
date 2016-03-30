/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.services;

import cnv.bigcom.BigCommerceClient;
import java.io.IOException;
import java.util.Scanner;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class BaseService {

    protected BigCommerceClient client;
    protected String baseUrl;

    BaseService() {
    }

    public BigCommerceClient getClient() {
        return client;
    }

    public void setClient(BigCommerceClient client) {
        this.client = client;
        this.baseUrl = client.getBaseUrl();
    }

    protected String execute(HttpUriRequest request) throws IOException {
        boolean debug = client.isDebugEnabled();
        StringBuilder buff = new StringBuilder();
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/json");
        // setting the access token IFF the credentials has it 
        if (client.getCredentials().hasAccessToken()) {
            request.addHeader("X-Auth-Client", client.getCredentials().getClientId());
            request.addHeader("X-Auth-Token", client.getCredentials().getAccessToken());
        } else {
            request.addHeader("Authorization", client.getCredentials().getBasicAuth());
        }
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            Scanner in = new Scanner(entity.getContent());
            if (debug) {
                System.out.println("Status Line: " + request.getRequestLine());
                System.out.println("Status Code: " + response.getStatusLine());
            }
            while (in.hasNext()) {
                String line = in.nextLine();
                buff.append(line);
            }
            if (debug) {
                System.out.println(buff.toString());
            }
        } catch (Exception e) {
        } finally {
            httpClient.close();
        }
        return buff.toString();
    }
}
