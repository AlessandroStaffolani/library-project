package it.astaffolani.LibraryProject.controller;

import com.fasterxml.jackson.databind.JsonNode;
import it.astaffolani.LibraryProject.entity.BookEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface BookControllerLocal {

    public BookEntity insert(BookEntity book);
    public BookEntity insert(JsonNode jsonBook);
    public List<BookEntity> findAll();
    public BookEntity findById(long id);
}
