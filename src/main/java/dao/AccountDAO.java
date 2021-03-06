package dao;

import entity.Account;

import javax.sql.DataSource;
import java.util.List;

public interface AccountDAO extends DAO {
    Account create(Account account);
    Account getAccount(String login);
    List<Account> listAccounts();
    void update(Account account);
    void delete(Account account);
}
