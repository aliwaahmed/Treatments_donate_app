package com.logapps.treatments_donate_app.Person.needs_data;

public class Ineed_class {

    private String need_name ;
    private String need_details ;
    private String phone_number ;
    private String address ;
    private String need_image ;
    private String prof_image ;
    private String need_expire ;
    private String get_expire_image ;
    private String id ;
    private String id_image ;
    public Ineed_class(String need_details, String need_name, String phone_number, String address, String need_image, String need_expire, String get_expire_image, String prof_image, String id,String id_image) {
        this.need_details = need_details;
        this.need_name = need_name;
        this.phone_number = phone_number;
        this.address = address;
        this.id_image=id_image;
        this.need_image = need_image;
        this.prof_image = prof_image;
        this.need_expire = need_expire;
        this.get_expire_image = get_expire_image;
        this.id = id;
    }

    public String getNeed_details() {
        return need_details;
    }

    public void setNeed_details(String need_details) {
        this.need_details = need_details;
    }

    public String getNeed_name() {
        return need_name;
    }

    public void setNeed_name(String need_name) {
        this.need_name = need_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeed_image() {
        return need_image;
    }

    public void setNeed_image(String need_image) {
        this.need_image = need_image;
    }

    public String getId_image() {
        return id_image;
    }

    public void setId_image(String id_image) {
        this.id_image = id_image;
    }

    public String getNeed_expire() {
        return need_expire;
    }

    public void setNeed_expire(String need_expire) {
        this.need_expire = need_expire;
    }

    public String getGet_expire_image() {
        return get_expire_image;
    }

    public void setGet_expire_image(String get_expire_image) {
        this.get_expire_image = get_expire_image;
    }

    public String getProf_image() {
        return prof_image;
    }

    public void setProf_image(String prof_image) {
        this.prof_image = prof_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ineed_class{" +
                "need_details='" + need_details + '\'' +
                ", need_name='" + need_name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", address='" + address + '\'' +
                ", need_image='" + need_image + '\'' +
                ", need_expire='" + need_expire + '\'' +
                ", get_expire_image='" + get_expire_image + '\'' +
                ", prof_image='" + prof_image + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

