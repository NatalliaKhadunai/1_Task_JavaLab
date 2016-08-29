package service;

import dao.CommentDAO;
import entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDAO commentDAO;

    public void addComment(int articleId, String userLogin, Timestamp publishDate, String content) {
        Comment comment = new Comment();
        comment.setArticleID(articleId);
        comment.setAccountLogin(userLogin);
        comment.setDate(publishDate);
        comment.setContent(content);
        addComment(comment);
    }

    public void addComment(Comment comment) {
        commentDAO.create(comment);
    }
}
