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
public class Product extends BaseModel {

    @SerializedName("sku")
    private String sku;
    @SerializedName("name")
    private String name;
    @SerializedName("custom_url")
    private String url;
    @SerializedName("primary_image")
    private ProductImage image;

    public ProductImage getImage() {
        return image;
    }

    public void setImage(ProductImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public class ProductImage extends BaseModel {

        @SerializedName("tiny_url")
        private String tiny;
        @SerializedName("standard_url")
        private String standard;
        @SerializedName("thumbnail_url")
        private String thumbnail;

        public String getStandard() {
            return standard;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public String getTiny() {
            return tiny;
        }
    }
}
