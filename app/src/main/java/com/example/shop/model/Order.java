package com.example.shop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("idorder")
@Expose
private Integer idorder;
@SerializedName("idproduct")
@Expose
private Integer idproduct;
@SerializedName("productname")
@Expose
private String productname;
@SerializedName("productprice")
@Expose
private Integer productprice;
@SerializedName("productnumber")
@Expose
private Integer productnumber;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getIdorder() {
return idorder;
}

public void setIdorder(Integer idorder) {
this.idorder = idorder;
}

public Integer getIdproduct() {
return idproduct;
}

public void setIdproduct(Integer idproduct) {
this.idproduct = idproduct;
}

public String getProductname() {
return productname;
}

public void setProductname(String productname) {
this.productname = productname;
}

public Integer getProductprice() {
return productprice;
}

public void setProductprice(Integer productprice) {
this.productprice = productprice;
}

public Integer getProductnumber() {
return productnumber;
}

public void setProductnumber(Integer productnumber) {
this.productnumber = productnumber;
}

}