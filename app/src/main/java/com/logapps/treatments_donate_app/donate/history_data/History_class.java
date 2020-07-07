package com.logapps.treatments_donate_app.donate.history_data;

public class History_class {
    private String name ;
    private String date ;

    public History_class(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "History_class{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}
