package dao;

import entity.Account;
import entity.Article;
import entity.Author;
import entity.Tag;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

public interface ArticleDAO extends DAO {
    void create(Article article, Set<Author> authorSet, Set<Tag> tagSet);
    Article getArticleById(int id);
    List<Article> listArticles();
    List<Article> listArticlesSortByDate();
    void delete(Article article);
}
