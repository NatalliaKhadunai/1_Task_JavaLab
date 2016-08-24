package dao;

import entity.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {
    public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Account account = new Account();
        account.setLogin(resultSet.getString("login"));
        account.setPassword(resultSet.getInt("password"));
        return account;
    }
}