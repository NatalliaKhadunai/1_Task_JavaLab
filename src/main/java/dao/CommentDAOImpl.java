package dao;

import entity.Account;
import entity.Article;
import entity.Comment;
import manager.SQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public class CommentDAOImpl implements CommentDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(Article article, Account account, Comment comment) {
        String SQL = SQLQueryManager.getProperty("CommentDAO.addComment");
        jdbcTemplateObject.update( SQL, article.getId(), account.getLogin(),
                comment.getDate(), comment.getContent());
    }

    public List<Comment> listCommentByArticleId(Article article) {
        String SQL = SQLQueryManager.getProperty("CommentDAO.getCommentsByArticleId");
        List<Comment> commentList = jdbcTemplateObject.query(SQL, new CommentMapper(), article.getId());
        return commentList;
    }

    public List<Comment> listCommentByAccountLogin(Account account) {
        String SQL = SQLQueryManager.getProperty("CommentDAO.getCommentsByAccountLogin");
        List<Comment> commentList = jdbcTemplateObject.query(SQL, new CommentMapper(), account.getLogin());
        return commentList;
    }

    public void delete(Article article, Account account, Comment comment){
        String SQL = SQLQueryManager.getProperty("CommentDAO.deleteComment");
        jdbcTemplateObject.update(SQL, article.getId(), account.getLogin(),
                comment.getDate(), comment.getContent());
    }
}
