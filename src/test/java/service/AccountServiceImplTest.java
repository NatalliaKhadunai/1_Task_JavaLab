package service;

import dao.AccountDAO;
import dao.AuthorDAO;
import entity.Account;
import exception.InvalidAccountException;
import exception.WrongLoginOrPasswordException;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})

public class AccountServiceImplTest {
    @Autowired
    AccountDAO accountDAO;
    AccountServiceImpl accountService;

    @Before
    public void setUp() throws Exception {
        accountDAO = mock(AccountDAO.class);
        accountService = new AccountServiceImpl();
        accountService.setAccountDAO(accountDAO);
    }

    @Test
    public void createAccountTest() {
        Account account = new Account();
        when(accountDAO.create(any(Account.class))).thenReturn(account);
        Account accountTest = accountService.create(new Account());
        assertEquals(accountTest, account);
    }

    @Test (expected = InvalidAccountException.class)
    public void createAccountNegativeTest() {
        accountService.create(null);
    }

    @Test
    public void getAccountTest() {
        Account account = new Account();
        when(accountDAO.getAccount(anyString())).thenReturn(account);
        Account accountTest = accountService.getAccount("1");
        assertEquals(account, accountTest);
    }

    @Test (expected = WrongLoginOrPasswordException.class)
    public void getAccountNegativeTest() {
        accountService.getAccount("            ");
    }

    @Test
    public void listAccountsTest() {
        when(accountDAO.listAccounts()).thenReturn(new ArrayList<Account>() {
            {
                add(new Account());
            }
        });
        List<Account> accountList = accountService.listAccounts();
        assertTrue(accountList.size() > 0);
    }

    @Test (expected = InvalidAccountException.class)
    public void updateAccountNegativeTest_NullAccount() {
        accountService.update(null);
    }

    @Test (expected = WrongLoginOrPasswordException.class)
    public void updateAccountNegativeTest_WrongLogin() {
        Account account = new Account();
        account.setLogin("");
        accountService.update(account);
    }

    @Test (expected = InvalidAccountException.class)
    public void deleteAccountNegativeTest_NullAccount() {
        accountService.delete(null);
    }

    @Test (expected = WrongLoginOrPasswordException.class)
    public void deleteAccountNegativeTest_WrongLogin() {
        Account account = new Account();
        account.setLogin("");
        accountService.delete(account);
    }
}
