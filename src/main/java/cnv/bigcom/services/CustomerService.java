/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.services;

import cnv.bigcom.ResponseParser;
import cnv.bigcom.model.Address;
import cnv.bigcom.model.Customer;
import com.google.gson.reflect.TypeToken;
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
public class CustomerService extends BaseService {

    public List<Customer> getCustomers() throws Exception {
        return getCustomers(250);
    }

    public List<Customer> getCustomers(int limit) throws Exception {
        return getCustomers(1, limit);
    }

    public List<Customer> getCustomers(int page, int limit) throws Exception {
        if (page < 1) {
            throw new IllegalArgumentException("Page should be a positive integer and > 0");
        }
        if (limit < 1) {
            throw new IllegalArgumentException("Limit should be a positive integer and > 0");
        }
        String path = "/customers";
        HttpUriRequest build = RequestBuilder.get().setUri(client.getBaseUrl() + path).
                addParameter("page", "" + page).
                addParameter("limit", "" + limit).
                build();
        Type type = new TypeToken<List<Customer>>() {
        }.getType();
        List<Customer> data = ResponseParser.parser().parse(execute(build), type);
        if (data != null && !data.isEmpty()) {
            for (Customer cust : data) {
                cust.setAddresses(getAddress(cust.getId()));
            }
        }
        return data;
    }

    private List<Address> getAddress(long custId) throws Exception {
        String path = "/customers/" + custId + "/addresses";
        HttpUriRequest build = RequestBuilder.get().setUri(client.getBaseUrl() + path).
                build();
        Type type = new TypeToken<List<Address>>() {
        }.getType();
        String json = execute(build);
        return ResponseParser.parser().parse(json, type);
    }

    public int getCount() throws Exception {
        String path = "/customers/count";
        HttpUriRequest build = RequestBuilder.get().setUri(client.getBaseUrl() + path).
                build();
        Type type = new TypeToken<HashMap>() {
        }.getType();
        Map map = ResponseParser.parser().parse(execute(build), type);
        return (map != null ? ((Number) map.get("count")).intValue() : 0);
    }
}