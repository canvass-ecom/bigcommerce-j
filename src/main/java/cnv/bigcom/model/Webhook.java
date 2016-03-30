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
public class Webhook extends BaseModel {

    @SerializedName("scope")
    private String scope;
    @SerializedName("destination")
    private String url;
    @SerializedName("format")
    private String format;
    @SerializedName("is_active")
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
