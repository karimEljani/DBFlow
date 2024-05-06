package com.ibizabroker.lms.service;

import com.ibizabroker.lms.dao.UsersRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public void deleteUser(Integer userId) {
        // Logique de suppression de l'utilisateur dans la base de données
        usersRepository.deleteById(userId);
    }

   // public List<String> getRoles() {
        // Utiliser le référentiel pour récupérer les rôles depuis la base de données
    //    List<String> roles = usersRepository.getAllRoles();
    //    return roles;
    //}

}
