package com.logapps.treatments_donate_app.donate.data;

public class All_needs_class {
    private String Name ;
    private String Details ;
    private String Img ;
    private String Proof ;
    private String Address ;
    private String Phone_number ;

    public All_needs_class(String name, String details, String img, String proof, String address, String phone_number) {
        Name = name;
        Details = details;
        Img = img;
        Proof = proof;
        Address = address;
        Phone_number = phone_number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getProof() {
        return Proof;
    }

    public void setProof(String proof) {
        Proof = proof;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "All_needs_class{" +
                "Name='" + Name + '\'' +
                ", Details='" + Details + '\'' +
                ", Img='" + Img + '\'' +
                ", Proof='" + Proof + '\'' +
                ", Address='" + Address + '\'' +
                ", Phone_number='" + Phone_number + '\'' +
                '}';
    }
}
