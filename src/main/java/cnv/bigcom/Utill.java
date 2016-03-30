/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom;

import cnv.bigcom.model.BaseModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class Utill {

    public static Gson getGson() {
        return new GsonBuilder().setDateFormat("E, dd MMM yyyy HH:mm:ss z").create();
    }

    public static <T extends BaseModel> String getFieldsAsCsv(Class<T> cls) {
        StringBuilder csv = new StringBuilder();
        if (cls == BaseModel.class) {
        } else {
            csv.append(getFieldsAsCsv((Class<BaseModel>) cls.getSuperclass()));
        }
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(SerializedName.class)) {
                SerializedName name = field.getAnnotation(SerializedName.class);
                //System.out.println(field.getType().isAssignableFrom(ShopifyBaseModal.class));
                csv.append(name.value()).append(",");
            }
        }
        return csv.toString();
    }

    public static void main(String[] args) throws ParseException {
        //System.out.println(getFieldsAsCsv(Order.class));
        DateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        //System.out.println(df.format(new Date()));
        System.out.println(df.parse("Tue, 29 Mar 2016 07:18:03 +0000"));
    }
}
