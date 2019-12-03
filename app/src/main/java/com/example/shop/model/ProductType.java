package com.example.shop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductType implements Serializable {

    @SerializedName("idtype")
    @Expose
    private String idtype;
    @SerializedName("producttypename")
    @Expose
    private String producttypename;
    @SerializedName("producttypeimage")
    @Expose
    private String producttypeimage;

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getProducttypename() {
        return producttypename;
    }

    public void setProducttypename(String producttypename) {
        this.producttypename = producttypename;
    }

    public String getProducttypeimage() {
        return producttypeimage;
    }

    public void setProducttypeimage(String producttypeimage) {
        this.producttypeimage = producttypeimage;
    }

}