package service;

import static org.junit.Assert.*;

import dao.ArticleDAO;
import dao.CommentDAO;
import entity.Article;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-config.xml"})
public class ArticleServiceImplTest {
    ArticleServiceImpl articleService;
    ArticleDAO articleDAO;
    CommentDAO commentDAO;

    @Before
    public void setUp() throws Exception {
        articleDAO = mock(ArticleDAO.class);
        commentDAO = mock(CommentDAO.class);
        articleService = new ArticleServiceImpl();
        articleService.setArticleDAO(articleDAO);
        articleService.setCommentDAO(commentDAO);
    }

    @Test
    public void getArticlesSortByDateTest() {
        when(articleDAO.listArticlesSortByDate()).thenReturn(new ArrayList<Article>() {
            {
                add(new Article());
            }
        });
        List<Article> articleList = articleService.getArticlesSortByDate();
        assertEquals(articleList.isEmpty(), false);
    }


}
