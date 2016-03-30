/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.services;

import cnv.bigcom.ResponseParser;
import cnv.bigcom.model.Store;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class StoreService extends BaseService {

    public Store getShop() throws IOException {
        String path = "/store";
        HttpUriRequest build = RequestBuilder.get().setUri(baseUrl + path).
                //addParameter("fields", ShopifyUtil.getFieldsAsCsv(Shop.class)).
                build();
        Type type = new TypeToken<Store>() {
        }.getType();
        return ResponseParser.parser().parse(execute(build), type);
    }
}
