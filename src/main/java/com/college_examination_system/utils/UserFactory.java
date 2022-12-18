package com.college_examination_system.utils;

import com.college_examination_system.models.Admin;
import com.college_examination_system.models.Lecturer;
import com.college_examination_system.models.Student;
import com.college_examination_system.models.User;

public class UserFactory {
    public static User createUser(String userType) {
        if(userType == null || userType.isEmpty())
            return null;
        return switch (userType.toLowerCase()) {
            case "admin" -> new User();
            case "student" -> new Student();
            case "lecturer" -> new Lecturer();
            default -> throw new IllegalArgumentException("NOT VALID USER ROLE" + userType);
        };
    }
}
