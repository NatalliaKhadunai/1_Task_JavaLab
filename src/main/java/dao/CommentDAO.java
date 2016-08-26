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
    void create(Comment comment);
    List<Comment> listCommentByArticleId(int id);
    List<Comment> listCommentByAccountLogin(String login);
    void delete(Comment comment);
}
