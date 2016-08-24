package dao;

import entity.Article;
import entity.Author;
import manager.SQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public class AuthorDAOImpl implements AuthorDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(Author author) {
        String SQL = SQLQueryManager.getProperty("AuthorDAO.addAuthor");
        jdbcTemplateObject.update( SQL, author.getFirst_name(), author.getLast_name());
    }

    public Author getAuthor(int id) {
        String SQL = SQLQueryManager.getProperty("AuthorDAO.getAuthor");
        Author author = jdbcTemplateObject.queryForObject(SQL, new AuthorMapper(), id);
        return author;
    }

    public List<Author> listAuthors() {
        String SQL = SQLQueryManager.getProperty("AuthorDAO.getAllAuthors");
        List<Author> authorList = jdbcTemplateObject.query(SQL, new AuthorMapper());
        return authorList;
    }

    public void delete(Author author){
        String SQL = SQLQueryManager.getProperty("AuthorDAO.deleteAuthor");
        jdbcTemplateObject.update(SQL, author.getId());
    }
}
