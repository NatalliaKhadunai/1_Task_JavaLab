package service;

import entity.Comment;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
public interface CommentService {
    Comment addComment(Comment comment);
    List<Comment> listCommentByArticleId(int id);
    List<Comment> listCommentByAccountLogin(String login);
    void delete(Comment comment);
}
