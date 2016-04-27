/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class PingModel extends BaseModel {

    @SerializedName("time")
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
