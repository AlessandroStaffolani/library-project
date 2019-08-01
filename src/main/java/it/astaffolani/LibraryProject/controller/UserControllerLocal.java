package it.astaffolani.LibraryProject.controller;

import it.astaffolani.LibraryProject.entity.UserEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserControllerLocal {

    public UserEntity insert(UserEntity user);
    public List<UserEntity> findAll();
    public UserEntity findById(long id);
    public UserEntity findByUsername(String username);
    public UserEntity findByEmail(String email);
}
