package service;

import command.NewsListSortedByDateCommand;
import dao.*;
import entity.Account;
import entity.Article;
import entity.Author;
import entity.Comment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet (name = "NewsServlet", urlPatterns = {"/NewsServlet"})
public class NewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-config.xml");
        PrintWriter printWriter = resp.getWriter();

       /* AccountDAOImpl accountDAO = ctx.getBean("accountDAOImpl", AccountDAOImpl.class);
        List<Account> accountList = accountDAO.listAccounts();
        printWriter.print(accountList.toString());*/


        /*List<Article> articleList = articleDAO.listArticles();
        printWriter.print(accountList.toString());*/

        /*AuthorDAOImpl authorDAO = ctx.getBean("authorDAOImpl", AuthorDAOImpl.class);
        List<Author> authorList = authorDAO.listAuthors();
        printWriter.print(authorList.toString());*/

        ArticleDAOImpl articleDAO = ctx.getBean("articleDAOImpl", ArticleDAOImpl.class);
        NewsListSortedByDateCommand command = new NewsListSortedByDateCommand(articleDAO);
        command.execute(req, resp);
    }
}
