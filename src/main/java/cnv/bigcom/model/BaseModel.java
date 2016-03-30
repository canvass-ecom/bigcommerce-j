/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public abstract class BaseModel implements Serializable {

    @SerializedName(value = "id")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
