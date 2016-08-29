package service;

import dao.ArticleDAO;
import dao.CommentDAO;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTML;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
        return articleDAO.getArticleById(id);
    }

    public Article addArticle(Article article, Set<Author> authorSet, Set<Tag> tagSet) {
        return articleDAO.create(article, authorSet, tagSet);
    }

    public void editArticle(Article articleToEdit) {
        articleDAO.editArticle(articleToEdit);
    }

    public void deleteArticle(Article... articles) {
        for (Article article : articles) {
            articleDAO.delete(article);
        }
    }

    public int totalNewsCount() {
        return articleDAO.getTotalCount();
    }
}
