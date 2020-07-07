package com.logapps.treatments_donate_app.donate.ph_donates;

public class Ph_Donates_class {
    private String name ;
    private String price ;
    private String details ;
    private String image ;
    private String date ;
    private String phone ;
    private String address ;

    public Ph_Donates_class(String name, String price, String details, String image, String date, String phone, String address) {
        this.name = name;
        this.price = price;
        this.details = details;
        this.image = image;
        this.date = date;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Ph_Donates_class{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", details='" + details + '\'' +
                ", image='" + image + '\'' +
                ", date='" + date + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
