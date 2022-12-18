package com.college_examination_system.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lecturer extends User {

    private StringProperty name;

    public Lecturer() {
        super();
        this.name = new SimpleStringProperty();
    }
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty() {
        return name;
    }
}
