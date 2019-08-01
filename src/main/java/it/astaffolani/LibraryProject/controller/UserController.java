package it.astaffolani.LibraryProject.controller;

import it.astaffolani.LibraryProject.data.UserRepositoryBean;
import it.astaffolani.LibraryProject.entity.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserController implements UserControllerLocal {

    @EJB
    private UserRepositoryBean userRepository;

    @Override
    public UserEntity insert(UserEntity user) {
        return userRepository.insert(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
