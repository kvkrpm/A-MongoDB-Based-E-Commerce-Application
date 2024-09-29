package com.jtspringproject.JtSpringProject.dao;

import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            // Log the exception and rethrow if necessary
            e.printStackTrace();
            return null;
        }
    }

    public User saveUser(User user) {
        try {
            userRepository.save(user);
            System.out.println("User added: " + user.getId());
            return user;
        } catch (Exception e) {
            // Log the exception and rethrow if necessary
            e.printStackTrace();
            return null;
        }
    }

    public User getUser(String username, String password) {
        try {
            User user = userRepository.findByUsername(username);
            if (user != null && password.equals(user.getPassword())) {
                return user;
            }
            return null;
        } catch (Exception e) {
            // Log the exception and rethrow if necessary
            e.printStackTrace();
            return null;
        }
    }

    public boolean userExists(String username) {
        try {
            return userRepository.existsByUsername(username);
        } catch (Exception e) {
            // Log the exception and rethrow if necessary
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            // Log the exception and rethrow if necessary
            e.printStackTrace();
            return null;
        }
    }
}
