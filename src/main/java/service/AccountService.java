package service;

import entity.Account;

import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
public interface AccountService {
    Account create(Account account);
    Account getAccount(String login);
    List<Account> listAccounts();
    void update(Account account);
    void delete(Account account);
}
