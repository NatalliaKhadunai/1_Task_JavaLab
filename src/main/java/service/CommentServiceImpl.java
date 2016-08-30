package service;

import dao.CommentDAO;
import entity.Comment;
import exception.InvalidCommentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDAO commentDAO;

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public Comment addComment(Comment comment) {
        if (comment != null) return commentDAO.create(comment);
        else throw new InvalidCommentException("Comment shouldn't be null");
    }

    public List<Comment> listCommentByArticleId(int id) {
        return commentDAO.listCommentByArticleId(id);
    }

    public List<Comment> listCommentByAccountLogin(String login) {
        return commentDAO.listCommentByAccountLogin(login);
    }

    public void delete(Comment comment) {
        if (comment != null) commentDAO.delete(comment);
        else throw new InvalidCommentException("Comment shouldn't be null");
    }


}
