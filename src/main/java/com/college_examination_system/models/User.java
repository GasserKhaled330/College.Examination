package com.college_examination_system.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private IntegerProperty id;
    private StringProperty email;
    private StringProperty password;
    private StringProperty userType;
    public User() {
        this.id = new SimpleIntegerProperty();
        this.email = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.userType = new SimpleStringProperty();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setUserType(String userType) {
        this.userType.set(userType);
    }

    public int getId() {
        return id.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPassword() {
        return password.get();
    }

    public String getUserType() {
        return userType.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty userTypeProperty() {
        return userType;
    }
}

