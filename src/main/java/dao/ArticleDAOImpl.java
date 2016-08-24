package dao;

import entity.Account;
import entity.Article;
import manager.SQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public class ArticleDAOImpl implements ArticleDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(Article article) {
        String SQL = SQLQueryManager.getProperty("ArticleDAO.addArticle");
        jdbcTemplateObject.update( SQL, article.getMainTitle(), article.getShortTitle(),
                article.getContent(), article.getPublishDate(), article.getMainPhoto());
    }

    public Article getArticleById(int id) {
        String SQL = SQLQueryManager.getProperty("ArticleDAO.getArticle");
        Article article = jdbcTemplateObject.queryForObject(SQL, new ArticleMapper(), id);
        return article;
    }

    public List<Article> listArticles() {
        String SQL = SQLQueryManager.getProperty("ArticleDAO.getAllArticles");
        List<Article> articleList = jdbcTemplateObject.query(SQL, new ArticleMapper());
        return articleList;
    }

    public void delete(Article article){
        String SQL = SQLQueryManager.getProperty("ArticleDAO.deleteArticle");
        jdbcTemplateObject.update(SQL, article.getId());
    }
}
