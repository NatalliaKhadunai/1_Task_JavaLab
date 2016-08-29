package dao;

import entity.Account;
import entity.Author;

import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public interface AuthorDAO extends DAO {
    Author create(Author author);
    Author getAuthor(int id);
    List<Author> listAuthors();
    void update(Author author);
    void delete(Author author);
}
