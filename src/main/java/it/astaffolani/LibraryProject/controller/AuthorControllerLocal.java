package it.astaffolani.LibraryProject.controller;

import it.astaffolani.LibraryProject.entity.AuthorEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AuthorControllerLocal {

    public AuthorEntity insert(AuthorEntity author);
    public List<AuthorEntity> findAll();
    public AuthorEntity findById(long id);
}
