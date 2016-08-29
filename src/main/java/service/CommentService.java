package service;

import entity.Comment;

import java.sql.Timestamp;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
public interface CommentService {
    void addComment(int articleId, String userLogin, Timestamp publishDate, String content);
    void addComment(Comment comment);
}
