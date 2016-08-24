package dao;

import entity.Account;
import entity.Article;

import javax.sql.DataSource;
import java.util.List;

public interface ArticleDAO extends DAO {
    void create(Article article);
    Article getArticleById(int id);
    List<Article> listArticles();
    void delete(Article article);
}
