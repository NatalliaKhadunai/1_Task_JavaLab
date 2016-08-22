package dao;

import entity.User;

import javax.sql.DataSource;
import java.util.List;

public interface UserDAO {
    void setDataSource(DataSource ds);
    void create(String name, int password);
    User getUser(String login);
    List<User> listUsers();
    void delete(String login);
}
