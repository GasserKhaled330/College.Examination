package com.college_examination_system.services;

import com.college_examination_system.models.User;
import com.college_examination_system.repositories.UserRepository;

public class UserService {
    public static boolean isUserExist(String userName,String password,String userType) {
        return UserRepository.verifyUser(userName, password,userType);
    }

}
