package com.college_examination_system.models;

import javafx.collections.ObservableList;

public class Course {
    private String courseId;

    private String subjectId;

    private int courseNumber;
    private String courseTitle;
    private int numberOfCredits;

    private ObservableList<Student> students;
    private Lecturer teacher;



}
