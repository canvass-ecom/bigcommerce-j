/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.services;

import cnv.bigcom.ResponseParser;
import cnv.bigcom.WebhookEvents.Order;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class OrderService extends BaseService {

    public List<Order> getOrders(List<Long> orderIds) throws Exception {
        List<Order> orders = null;
        if (orderIds != null && !orderIds.isEmpty()) {
            String path = "/orders";
            StringBuilder orderIdsString = new StringBuilder();
            for (int i = 0; i < orderIds.size(); i++) {
                if (i != 0) {
                    orderIdsString.append(",");
                }
                orderIdsString.append(orderIds.get(i));
            }
            RequestBuilder builder = RequestBuilder.get().setUri(client.getBaseUrl() + path).
                    addParameter("ids", orderIdsString.toString());
            //.addParameter("fields", ShopifyUtil.getFieldsAsCsv(Order.class));
            HttpUriRequest build = builder.build();
            Type type = new TypeToken<HashMap<String, List<Order>>>() {
            }.getType();
            HashMap<String, List<Order>> parse = ResponseParser.parser().parse(execute(build), type);
            orders = (parse != null && parse.get("orders") != null) ? parse.get("orders") : null;
        }
        return orders;
    }
}