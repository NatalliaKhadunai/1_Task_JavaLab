package service;

import dao.AccountDAO;
import entity.Account;
import exception.InvalidAccountException;
import exception.WrongLoginOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO accountDAO;

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account create(Account account) {
        if (account != null) return accountDAO.create(account);
        else throw new InvalidAccountException("Account shouldn't be null");
    }

    public Account getAccount(String login) {
        if (login.trim().length() != 0) return accountDAO.getAccount(login);
        else throw new WrongLoginOrPasswordException();
    }

    public List<Account> listAccounts() {
        return accountDAO.listAccounts();
    }

    public void update(Account account) {
        if (account == null) throw new InvalidAccountException("Account shouldn't be null");
        if (account.getLogin().length() == 0) throw new WrongLoginOrPasswordException();
        accountDAO.update(account);
    }

    public void delete(Account account) {
        if (account == null) throw new InvalidAccountException("Account shouldn't be null");
        if (account.getLogin().length() == 0) throw new WrongLoginOrPasswordException();
        accountDAO.delete(account);
    }
}
