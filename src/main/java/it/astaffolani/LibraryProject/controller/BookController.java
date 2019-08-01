package it.astaffolani.LibraryProject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.astaffolani.LibraryProject.data.BookRepositoryBean;
import it.astaffolani.LibraryProject.entity.AuthorEntity;
import it.astaffolani.LibraryProject.entity.BookEntity;
import it.astaffolani.LibraryProject.utils.LoggerInterceptor;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Stateless
public class BookController implements BookControllerLocal {

    @EJB
    private BookRepositoryBean bookRepository;
    @EJB
    private AuthorControllerLocal authorController;

    @Override
    @Interceptors(LoggerInterceptor.class)
    public BookEntity insert(BookEntity book) {
        return bookRepository.insert(book);
    }

    @Override
    @Interceptors(LoggerInterceptor.class)
    public BookEntity insert(JsonNode jsonBook) {
        ObjectNode jsonObject = (ObjectNode) jsonBook;
        ArrayNode authors = (ArrayNode) jsonObject.get("authors");
        ObjectMapper mapper = new ObjectMapper();
        BookEntity book = new BookEntity();
        if (authors == null || authors.size() == 0) {
            jsonObject.remove("authors");
            try {
                book = mapper.treeToValue(jsonObject, BookEntity.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            List<String> authorIds = new ArrayList<>();
            Consumer<JsonNode> authorsConsumer = (JsonNode node) -> authorIds.add(node.asText());
            authors.forEach(authorsConsumer);
            List<AuthorEntity> authorEntities = new ArrayList<>();
            for (String id : authorIds) {
                authorEntities.add(authorController.findById(new Long(id)));
            }
            try {
                jsonObject.replace("authors", mapper.valueToTree(authorEntities));
                book = mapper.treeToValue(jsonObject, BookEntity.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return this.insert(book);
    }

    @Override
    @Interceptors(LoggerInterceptor.class)
    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Interceptors(LoggerInterceptor.class)
    public BookEntity findById(long id) {
        return bookRepository.findById(id);
    }
}
