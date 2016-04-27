/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.services;

import cnv.bigcom.ResponseParser;
import cnv.bigcom.model.Order;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class OrderService extends BaseService {

    /**
     * Retrivies the orders in the descending order of the order date
     *
     * @param page
     * @param limit
     * @return
     * @throws Exception
     */
    public List<Order> getOrders(int page, int limit) throws Exception {
        if (page < 1) {
            throw new IllegalArgumentException("Page should be a positive integer and > 0");
        }
        if (limit < 1) {
            throw new IllegalArgumentException("Limit should be a positive integer and > 0");
        }
        String path = "/orders";
        RequestBuilder builder = RequestBuilder.get().setUri(client.getBaseUrl() + path).
                addParameter("sort", "date_created:desc"); // sorting by latest order        
        HttpUriRequest build = builder.build();
        Type type = new TypeToken<List<Order>>() {
        }.getType();
        return ResponseParser.parser().parse(execute(build), type);
    }

    public int getCount() throws Exception {
        String path = "/orders/count";
        HttpUriRequest build = RequestBuilder.get().setUri(client.getBaseUrl() + path).
                build();
        Type type = new TypeToken<HashMap>() {
        }.getType();
        Map map = ResponseParser.parser().parse(execute(build), type);
        return (map != null ? ((Number) map.get("count")).intValue() : 0);
    }

    public List<Order.Item> getOrderItems(long orderId) throws IOException {
        String path = "/orders/" + orderId + "/products";
        RequestBuilder builder = RequestBuilder.get().setUri(client.getBaseUrl() + path);
        HttpUriRequest build = builder.build();
        Type type = new TypeToken<List<Order.Item>>() {
        }.getType();
        String json = execute(build);        
        return ResponseParser.parser().parse(json, type);
    }

    public List<Order> getOrders(List<Long> custIds) throws Exception {
        List<Order> orders = null;
        if (custIds != null && !custIds.isEmpty()) {
            String path = "/orders";
            StringBuilder orderIdsString = new StringBuilder();
            for (int i = 0; i < custIds.size(); i++) {
                if (i != 0) {
                    orderIdsString.append(",");
                }
                orderIdsString.append(custIds.get(i));
            }
            RequestBuilder builder = RequestBuilder.get().setUri(client.getBaseUrl() + path).
                    addParameter("customer_id", orderIdsString.toString());
            //.addParameter("fields", ShopifyUtil.getFieldsAsCsv(Order.class));
            HttpUriRequest build = builder.build();
            Type type = new TypeToken<List<Order>>() {
            }.getType();
            orders = ResponseParser.parser().parse(execute(build), type);
        }
        return orders;
    }    
}