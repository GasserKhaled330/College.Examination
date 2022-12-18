package com.college_examination_system.services;

import com.college_examination_system.models.Lecturer;
import com.college_examination_system.repositories.LecturerRepository;
import javafx.collections.ObservableList;

public class LecturerService {

    public static Lecturer getLecturer(int lecturerId) {
        return LecturerRepository.getLecturer(lecturerId);
    }

    public static boolean isLecturerExist(int lecturerId ){
        Lecturer lecturer = getLecturer(lecturerId);
        return lecturer == null;
    }
    public static ObservableList<Lecturer> getLecturers() {
        return LecturerRepository.getLecturers();
    }

    public static boolean createLecturer(Lecturer lecturer) {
        return LecturerRepository.createLecturer(lecturer);
    }

    public static boolean updateLecturer(int id, Lecturer lecturer) {

        return LecturerRepository.updateLecturer(id, lecturer);
    }

    public static boolean deleteLecturer(int lecturerId) {
        return LecturerRepository.deleteLecturer(lecturerId);
    }
    
}
