package dao;

import entity.User;
import manager.SQLQueryManager;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserJDBCTemplate implements UserDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(String login, int password) {
        String SQL = SQLQueryManager.getProperty("UserDAO.addUser");
        jdbcTemplateObject.update( SQL, login, password);
        return;
    }

    public User getUser(String login) {
        String SQL = SQLQueryManager.getProperty("UserDAO.getUser");
        User user = jdbcTemplateObject.queryForObject(SQL, new UserMapper());
        return user;
    }

    public List<User> listUsers() {
        String SQL = SQLQueryManager.getProperty("UserDAO.getAllUsers");
        List<User> students = jdbcTemplateObject.query(SQL, new UserMapper());
        return students;
    }

    public void delete(String login){
        String SQL = SQLQueryManager.getProperty("UserDAO.deleteUser");
        jdbcTemplateObject.update(SQL, login);
        return;
    }
}
