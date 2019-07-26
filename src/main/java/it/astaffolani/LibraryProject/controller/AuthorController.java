package it.astaffolani.LibraryProject.controller;

import it.astaffolani.LibraryProject.data.AuthorRepositoryBean;
import it.astaffolani.LibraryProject.entity.AuthorEntity;
import it.astaffolani.LibraryProject.utils.LoggerInterceptor;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless
public class AuthorController implements AuthorControllerLocal {

    @EJB
    private AuthorRepositoryBean authorRepository;

    @Override
    @Interceptors(LoggerInterceptor.class)
    public AuthorEntity insert(AuthorEntity author) {
        return authorRepository.insert(author);
    }

    @Override
    @Interceptors(LoggerInterceptor.class)
    public List<AuthorEntity> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Interceptors(LoggerInterceptor.class)
    public AuthorEntity findById(long id) {
        return authorRepository.findById(id);
    }
}
