package dao;

import entity.Account;
import entity.Article;
import entity.Author;
import entity.Comment;

import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public interface CommentDAO extends DAO{
    void create(Article article, Account account, Comment comment);
    List<Comment> listCommentByArticleId(Article article);
    List<Comment> listCommentByAccountLogin(Account account);
    void delete(Article article, Account account, Comment comment);
}
