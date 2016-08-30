package service;

import dao.ArticleDAO;
import dao.CommentDAO;
import entity.*;
import exception.EmptySetException;
import exception.InvalidArticleException;
import exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    ArticleDAO articleDAO;
    CommentDAO commentDAO;

    @Autowired
    public void setArticleDAO(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }
    @Autowired
    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public List<Article> getArticlesSortByDate() {
        return articleDAO.listArticlesSortByDate();
    }

    public List<Article> getArticlesSortByNumOfComments() {
        List<Article> articleList = articleDAO.listArticles();
        for (Article article : articleList) {
            List<Comment> commentList = commentDAO.listCommentByArticleId(article.getId());
            article.setCommentList(commentList);
        }
        Collections.sort(articleList, new ArticleCommentComparator());
        return articleList;
    }

    public Article getArticle(int id) {
        if (id > 0) return articleDAO.getArticleById(id);
        else throw new InvalidIdException("Id should be more than 0");
    }

    public Article addArticle(Article article, Set<Author> authorSet, Set<Tag> tagSet) {
        if (article == null) throw new InvalidArticleException("Article shouldn't be null");
        if (authorSet == null || authorSet.isEmpty()) throw new EmptySetException("Set<Author> shouldn't be empty");
        if (tagSet == null || tagSet.isEmpty()) throw new EmptySetException("Set<Tag> shouldn't be empty");
        return articleDAO.create(article, authorSet, tagSet);
    }

    public void editArticle(Article articleToEdit) {
        if (articleToEdit == null) throw new InvalidArticleException("Article shouldn't be null");
        articleDAO.editArticle(articleToEdit);
    }

    public void deleteArticle(Article... articles) {
        if (articles == null) throw new InvalidArticleException("Articles shouldn't be null");
        for (Article article : articles) {
            articleDAO.delete(article);
        }
    }

    public int totalNewsCount() {
        return articleDAO.getTotalCount();
    }
}
