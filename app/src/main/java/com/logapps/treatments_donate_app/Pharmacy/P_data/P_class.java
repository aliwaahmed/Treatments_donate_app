package com.logapps.treatments_donate_app.Pharmacy.P_data;

public class P_class {

    private String Name ;
    private String Details ;
    private String Img ;
    private String Address ;
    private String Phone_number ;

    public P_class(String name, String details, String img, String address, String phone_number) {
        Name = name;
        Details = details;
        Img = img;
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
        return "P_class{" +
                "Name='" + Name + '\'' +
                ", Details='" + Details + '\'' +
                ", Img='" + Img + '\'' +
                ", Address='" + Address + '\'' +
                ", Phone_number='" + Phone_number + '\'' +
                '}';
    }
}
