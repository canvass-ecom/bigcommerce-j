/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.services;

import cnv.bigcom.ResponseParser;
import cnv.bigcom.model.Product;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class ProductService extends BaseService {

    public Product getProduct(long productId) throws IOException {
        String path = "/products/" + productId;
        HttpUriRequest build = RequestBuilder.get().setUri(baseUrl + path).                
                build();
        Type type = new TypeToken<Product>() {
        }.getType();
        return ResponseParser.parser().parse(execute(build), type);
    }
}
