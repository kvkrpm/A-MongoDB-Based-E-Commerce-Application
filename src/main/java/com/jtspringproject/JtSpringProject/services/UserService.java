package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    
    public List<User> getUsers() {
        return this.userDao.getAllUsers(); // Ensure method name in DAO is corrected
    }
    
    public User addUser(User user) {
        try {
            return this.userDao.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            // Log the exception and provide more details
            throw new RuntimeException("Error adding user: " + e.getMessage(), e);
        }
    }
    
    public User checkLogin(String username, String password) {
        return this.userDao.getUser(username, password);
    }

    public boolean checkUserExists(String username) {
        return this.userDao.userExists(username);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}
}
