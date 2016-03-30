/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom;

import com.google.gson.Gson;
import java.lang.reflect.Type;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class ResponseParser {

    private static final ResponseParser parser = new ResponseParser();
    private Gson gson;

    private ResponseParser() {
        gson = Utill.getGson();
    }

    public <T> T parse(String json, Class<T> cls) {
        T obj = null;
        try {
            obj = gson.fromJson(json, cls);
        } catch (Exception e) {
            System.out.println("Error while parsing the Shopify Reponse: " + e);
        }
        return obj;
    }

    public <T> T parse(String json, Type type) {
        T obj = null;
        try {
            obj = gson.fromJson(json, type);
        } catch (Exception e) {
            System.out.println("Error while parsing the Shopify Reponse: " + e);
        }
        return obj;
    }

    public static ResponseParser parser() {
        return parser;
    }
}