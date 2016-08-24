package dao;

import entity.Account;
import manager.SQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier ("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(Account account) {
        String SQL = SQLQueryManager.getProperty("AccountDAO.addAccount");
        jdbcTemplateObject.update( SQL, account.getLogin(), account.getPassword());
        return;
    }

    public Account getAccount(String login) {
        String SQL = SQLQueryManager.getProperty("AccountDAO.getAccount");
        Account user = jdbcTemplateObject.queryForObject(SQL, new AccountMapper(), login);
        return user;
    }

    public List<Account> listAccounts() {
        String SQL = SQLQueryManager.getProperty("AccountDAO.getAllAccounts");
        List<Account> accountList = jdbcTemplateObject.query(SQL, new AccountMapper());
        return accountList;
    }

    public void delete(Account account){
        String SQL = SQLQueryManager.getProperty("AccountDAO.deleteAccount");
        jdbcTemplateObject.update(SQL, account.getLogin());
        return;
    }
}
