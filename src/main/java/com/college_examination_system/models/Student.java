package com.college_examination_system.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student extends User {
    private StringProperty name;
    private StringProperty collegeId;

    public Student() {
        super();
        this.collegeId = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
    }

    public String getCollegeId() {
        return collegeId.get();
    }

    public void setCollegeId(String collegeId) {
        this.collegeId.set(collegeId);
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

    public StringProperty collegeIdProperty() {
        return collegeId;
    }
}
