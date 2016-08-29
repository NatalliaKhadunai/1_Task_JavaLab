package service;

import entity.Article;
import entity.Author;
import entity.Tag;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
public interface ArticleService {
    List<Article> getArticlesSortByDate();
    List<Article> getArticlesSortByNumOfComments();
    Article getArticle(int id);
    Article addArticle(Article article, Set<Author> authorSet, Set<Tag> tagSet);
    void editArticle(Article articleToEdit);
    void deleteArticle(Article ... articles);
    int totalNewsCount();
}
