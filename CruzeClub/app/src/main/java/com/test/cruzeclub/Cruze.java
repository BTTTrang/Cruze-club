package com.test.cruzeclub;

import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

public class Cruze {
    @SerializedName("fullname")
    public String txtFullName;
    @SerializedName("fb_name")
    public String txtFBName;
    @SerializedName("phone")
    public String txtPhone;
    @SerializedName("address")
    public String txtAddress;
    @SerializedName("gender")
    public String txtGender;
    @SerializedName("stamp")
    public String txtStamp;
    @SerializedName("plate")
    public String txtPlate;
    public Cruze(String txtFullName , String txtFBName , String txtPhone , String txtAddress , String txtGender , String txtStamp , String txtPlate) {
        this.txtFullName = txtFullName;
        this.txtFBName = txtFBName;
        this.txtPhone = txtPhone;
        this.txtAddress = txtAddress;
        this.txtGender = txtGender;
        this.txtStamp = txtStamp;
        this.txtPlate = txtPlate;
    }
}
