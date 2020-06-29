package com.logapps.treatments_donate_app.Person.Search;

public class Search_class {

    private String name;
    private String image;
    private String Treatment_name;
    private String id ;

    public Search_class(String name, String image, String treatment_name, String id) {
        this.name = name;
        this.image = image;
        Treatment_name = treatment_name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTreatment_name() {
        return Treatment_name;
    }

    public void setTreatment_name(String treatment_name) {
        Treatment_name = treatment_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Search_class{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", Treatment_name='" + Treatment_name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}