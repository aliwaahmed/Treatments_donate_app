package com.logapps.treatments_donate_app.Person.Accepted;

public class Accepted_Class {
    private String treat_name ;
    private String date ;

    public Accepted_Class(String treat_name, String date) {
        this.treat_name = treat_name;
        this.date = date;
    }

    public String getTreat_name() {
        return treat_name;
    }

    public void setTreat_name(String treat_name) {
        this.treat_name = treat_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Accepted_Class{" +
                "treat_name='" + treat_name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
