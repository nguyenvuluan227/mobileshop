package com.example.shop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("customername")
@Expose
private String customername;
@SerializedName("customerphone")
@Expose
private String customerphone;
@SerializedName("email")
@Expose
private String email;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getCustomername() {
return customername;
}

public void setCustomername(String customername) {
this.customername = customername;
}

public String getCustomerphone() {
return customerphone;
}

public void setCustomerphone(String customerphone) {
this.customerphone = customerphone;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

}