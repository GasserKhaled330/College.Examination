package com.college_examination_system.services;

import com.college_examination_system.models.User;
import com.college_examination_system.repositories.AdminRepository;

public class AdminService {
    public static User getAdminData() {
        return AdminRepository.getAdminData();
    }
    public static boolean updateAdmin(int id, String email, String password){
        return AdminRepository.updateAdmin(id, email, password);
    }
}
