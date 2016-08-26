package service;

import command.MostPopularNewsCommand;
import command.NewsListSortedByDateCommand;
import command.SearchNewsByTagsCommand;
import command.TotalNewsCountCommand;
import dao.ArticleDAO;
import entity.Article;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name = "NewsServlet", urlPatterns = {"/NewsServlet"})
public class NewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-config.xml");
        req.setAttribute("tags", "3");
        PrintWriter printWriter = resp.getWriter();
        SearchNewsByTagsCommand command = new SearchNewsByTagsCommand(ctx);
        command.execute(req, resp);
    }
}
