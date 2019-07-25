package it.astaffolani.LibraryProject.controller;

import it.astaffolani.LibraryProject.data.AuthorRepositoryBean;
import it.astaffolani.LibraryProject.entity.AuthorEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AuthorController implements AuthorControllerLocal {

    @EJB
    private AuthorRepositoryBean authorRepository;

    @Override
    public AuthorEntity insert(AuthorEntity author) {
        return authorRepository.insert(author);
    }

    @Override
    public List<AuthorEntity> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public AuthorEntity findById(long id) {
        return authorRepository.findById(id);
    }
}
