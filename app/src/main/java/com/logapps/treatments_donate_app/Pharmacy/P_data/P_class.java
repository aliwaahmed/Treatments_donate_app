package com.logapps.treatments_donate_app.Pharmacy.P_data;

public class P_class {

    private String Name ;
    private String Details ;
    private String Img ;
    private String Address ;
    private String Phone_number ;
    private String ef_material ;

    public P_class(String name, String details, String img, String address, String phone_number, String ef_material) {
        Name = name;
        Details = details;
        Img = img;
        Address = address;
        Phone_number = phone_number;
        this.ef_material = ef_material;
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

    public String getEf_material() {
        return ef_material;
    }

    public void setEf_material(String ef_material) {
        this.ef_material = ef_material;
    }

    @Override
    public String toString() {
        return "P_class{" +
                "Name='" + Name + '\'' +
                ", Details='" + Details + '\'' +
                ", Img='" + Img + '\'' +
                ", Address='" + Address + '\'' +
                ", Phone_number='" + Phone_number + '\'' +
                ", ef_material='" + ef_material + '\'' +
                '}';
    }
}
