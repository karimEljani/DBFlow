package com.ibizabroker.lms.service;

import com.ibizabroker.lms.dao.UsersRepository;
import com.ibizabroker.lms.entity.Users;
import com.ibizabroker.lms.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users addUser(Users user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            throw new IllegalArgumentException("The user must have at least one role.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public Users getUserById(int id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " does not exist."));
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users updateUser(int id, Users userDetails) {
        Users user = getUserById(id);

        user.setName(userDetails.getName());
        user.setUsername(userDetails.getUsername());
        user.setRole(userDetails.getRole());

        return usersRepository.save(user);
    }

    public void deleteUser(int id) {
        if (!usersRepository.existsById(id)) {
            throw new NotFoundException("User with id " + id + " does not exist.");
        }
        usersRepository.deleteById(id);
    }

    public void deleteMultipleUsers(List<Integer> userIds) {
        usersRepository.deleteAllByUserIdIn(userIds);
    }
}
