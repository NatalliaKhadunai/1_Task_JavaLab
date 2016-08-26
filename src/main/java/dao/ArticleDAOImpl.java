package dao;

import entity.Account;
import entity.Article;
import entity.Author;
import entity.Tag;
import manager.SQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

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

    public void create(Article article, List<Integer> authorIdList, Set<Integer> tagIdSet) {
        String SQL = SQLQueryManager.getProperty("ArticleDAO.addArticle");
        jdbcTemplateObject.update( SQL, article.getMainTitle(), article.getShortTitle(),
                article.getContent(), article.getPublishDate(), article.getMainPhoto());
        String SQLArticleAuthor = SQLQueryManager.getProperty("Article_Author.addRow");
        for (int authorId : authorIdList)
            jdbcTemplateObject.update( SQLArticleAuthor, article.getId(), authorId);
        String SQLArticleTag = SQLQueryManager.getProperty("Article_Tag.addRow");
        for (int tagId : tagIdSet)
            jdbcTemplateObject.update( SQLArticleAuthor, article.getId(), tagId);
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

    public List<Article> listArticlesSortByDate() {
        String SQL = SQLQueryManager.getProperty("ArticleDAO.getAllArticlesSortByDate");
        List<Article> articleList = jdbcTemplateObject.query(SQL, new ArticleMapper());
        return articleList;
    }

    public int getTotalCount() {
        String SQL = SQLQueryManager.getProperty("ArticleDAO.totalCount");
        int totalCount = jdbcTemplateObject.queryForObject(SQL, Integer.class);
        return totalCount;
    }

    public void delete(Article article){
        String SQL = SQLQueryManager.getProperty("ArticleDAO.deleteArticle");
        jdbcTemplateObject.update(SQL, article.getId());
    }
}
