/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.services;

import cnv.bigcom.ResponseParser;
import cnv.bigcom.Utill;
import cnv.bigcom.model.Webhook;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class WebhookService extends BaseService {

    public List<Webhook> getWebhooks() throws Exception {
        String path = "/hooks";
        HttpUriRequest build = RequestBuilder.get().setUri(baseUrl + path).
                //addParameter("fields", ShopifyUtil.getFieldsAsCsv(Webhook.class)).
                build();
        Type type = new TypeToken<List<Webhook>>() {
        }.getType();
        return ResponseParser.parser().parse(execute(build), type);
    }

    public Webhook createWebhook(String scope, String url) throws Exception {
        Webhook ww = new Webhook();
        ww.setScope(scope);
        ww.setUrl(url);
        ww.setActive(true);
        String path = "/hooks";
        HttpUriRequest build = RequestBuilder.post().setUri(baseUrl + path).
                setHeader("Content-Type", "application/json").
                setEntity(new StringEntity(Utill.getGson().toJson(ww))).
                build();
        String json = execute(build);
        System.out.println(json);
        return ResponseParser.parser().parse(json, Webhook.class);
    }

    public Webhook updateWebhook(int hookId, String url, boolean activeFlag) throws Exception {
        Webhook ww = new Webhook();
        if (url != null) {
            ww.setUrl(url);
        }
        ww.setActive(activeFlag);
        String path = "/hooks/" + hookId;
        HttpUriRequest build = RequestBuilder.put().setUri(baseUrl + path).
                setHeader("Content-Type", "application/json").
                setEntity(new StringEntity(Utill.getGson().toJson(ww))).
                build();
        Webhook hook = ResponseParser.parser().parse(execute(build), Webhook.class);
        return hook;
    }

    public void deleteWebhook(long hookId) throws Exception {
        String path = "/hooks/" + hookId;
        HttpUriRequest build = RequestBuilder.delete().setUri(baseUrl + path).
                build();
        execute(build);
    }
}
