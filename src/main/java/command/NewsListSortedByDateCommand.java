package command;

import dao.ArticleDAO;
import dao.ArticleDAOImpl;
import entity.Article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public class NewsListSortedByDateCommand implements ActionCommand {
    ArticleDAOImpl articleDAOImpl;

    public NewsListSortedByDateCommand(ArticleDAOImpl articleDAO) {
        this.articleDAOImpl = articleDAO;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        List<Article> articleList = articleDAOImpl.listArticlesSortByDate();
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(articleList.toString());
        }
        catch (IOException e) {

        }
    }
}
