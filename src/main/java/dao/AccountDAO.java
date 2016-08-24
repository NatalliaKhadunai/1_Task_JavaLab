package dao;

import entity.Account;

import javax.sql.DataSource;
import java.util.List;

public interface AccountDAO extends DAO {
    void create(Account account);
    Account getAccount(String login);
    List<Account> listAccounts();
    void delete(Account account);
}
