package dao;

import entity.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public class CommentMapper implements RowMapper<Comment> {
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setArticleID(resultSet.getInt("article_id"));
        comment.setAccountLogin(resultSet.getString("user_login"));
        comment.setContent(resultSet.getString("content"));
        comment.setDate(resultSet.getTimestamp("publish_date"));
        return null;
    }
}
