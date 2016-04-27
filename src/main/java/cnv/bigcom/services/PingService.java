/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.services;

import cnv.bigcom.ResponseParser;
import cnv.bigcom.model.PingModel;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class PingService extends BaseService {

    public PingModel ping() throws IOException {
        String path = "/time";
        HttpUriRequest build = RequestBuilder.get().setUri(baseUrl + path).
                //addParameter("fields", ShopifyUtil.getFieldsAsCsv(Shop.class)).
                build();
        Type type = new TypeToken<PingModel>() {
        }.getType();
        String resJson = execute(build);        
        return ResponseParser.parser().parse(resJson, type);
    }
}
