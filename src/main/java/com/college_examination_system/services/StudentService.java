package com.college_examination_system.services;

import com.college_examination_system.models.Student;
import com.college_examination_system.repositories.StudentRepository;
import javafx.collections.ObservableList;

public class StudentService {
    public static Student getStudent(String collegeId) {
        return StudentRepository.getStudent(collegeId);
    }

    public static boolean isStudentExist(String collegeId){
        Student student = getStudent(collegeId);
        return student == null;
    }
    public static ObservableList<Student> getStudents() {
        return StudentRepository.getStudents();
    }

    public static boolean createStudent(Student student) {
        return StudentRepository.createStudent(student);
    }

    public static void updateStudent(int id, Student student) {
         StudentRepository.updateStudent(id, student);
    }

    public static void deleteStudent(String collegeId) {
         StudentRepository.deleteStudent(collegeId);
    }
}
