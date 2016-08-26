package dao;

import entity.Account;
import entity.Article;
import entity.Author;
import entity.Tag;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

public interface ArticleDAO extends DAO {
    void create(Article article, List<Integer> authorIdList, Set<Integer> tagIdSet);
    Article getArticleById(int id);
    List<Article> listArticles();
    List<Article> listArticlesSortByDate();
    int getTotalCount();
    void delete(Article article);
}
