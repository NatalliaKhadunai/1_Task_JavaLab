package service;

import entity.Author;

import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
public interface AuthorService {
    Author create(Author author);
    Author getAuthor(int id);
    List<Author> listAuthors();
    void update(Author author);
    void delete(Author author);
}
