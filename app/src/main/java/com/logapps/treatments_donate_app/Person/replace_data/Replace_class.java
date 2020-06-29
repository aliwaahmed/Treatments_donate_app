package com.logapps.treatments_donate_app.Person.replace_data;

public class Replace_class {
    private String t_name ;
    private String t_details ;
    private String t_image ;
    private String t_expire ;
    private String prize ;
    private String getT_expire_image ;

    public Replace_class(String t_name, String t_details, String t_image, String t_expire, String prize, String getT_expire_image) {
        this.t_name = t_name;
        this.t_details = t_details;
        this.t_image = t_image;
        this.t_expire = t_expire;
        this.prize = prize;
        this.getT_expire_image = getT_expire_image;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_details() {
        return t_details;
    }

    public void setT_details(String t_details) {
        this.t_details = t_details;
    }

    public String getT_image() {
        return t_image;
    }

    public void setT_image(String t_image) {
        this.t_image = t_image;
    }

    public String getT_expire() {
        return t_expire;
    }

    public void setT_expire(String t_expire) {
        this.t_expire = t_expire;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getGetT_expire_image() {
        return getT_expire_image;
    }

    public void setGetT_expire_image(String getT_expire_image) {
        this.getT_expire_image = getT_expire_image;
    }

    @Override
    public String toString() {
        return "Replace_class{" +
                "t_name='" + t_name + '\'' +
                ", t_details='" + t_details + '\'' +
                ", t_image='" + t_image + '\'' +
                ", t_expire='" + t_expire + '\'' +
                ", prize='" + prize + '\'' +
                ", getT_expire_image='" + getT_expire_image + '\'' +
                '}';
    }
}
