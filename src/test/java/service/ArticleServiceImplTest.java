package service;

import static org.junit.Assert.*;

import dao.ArticleDAO;
import dao.CommentDAO;
import entity.Article;
import entity.Author;
import entity.Comment;
import entity.Tag;
import exception.EmptySetException;
import exception.InvalidArticleException;
import exception.InvalidIdException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class ArticleServiceImplTest {
    ArticleServiceImpl articleService;
    ArticleDAO articleDAO;
    CommentDAO commentDAO;
    List<Article> articleList;
    Article article;
    List<Comment> commentList;

    @Before
    public void setUp() throws Exception {
        articleDAO = mock(ArticleDAO.class);
        commentDAO = mock(CommentDAO.class);
        articleService = new ArticleServiceImpl();
        articleService.setArticleDAO(articleDAO);
        articleService.setCommentDAO(commentDAO);

        article = new Article();
        article.setId(1);
        articleList = new ArrayList<Article>();
        articleList.add(article);
        commentList = new ArrayList<Comment>();
        commentList.add(new Comment());
    }

    @Test
    public void getArticlesSortByDateTest() {
        when(articleDAO.listArticlesSortByDate()).thenReturn(articleList);
        List<Article> articleList = articleService.getArticlesSortByDate();
        assertEquals(articleList.isEmpty(), false);
    }

    @Test
    public void getArticlesSortByNumOfCommentsTest() {
        when(articleDAO.listArticles()).thenReturn(articleList);
        when(commentDAO.listCommentByArticleId(article.getId())).thenReturn(commentList);
        List<Article> articleList = articleService.getArticlesSortByNumOfComments();
        assertEquals(articleList.isEmpty(), false);
    }

    @Test
    public void getArticleByIdTest() {
        when(articleDAO.getArticleById(article.getId())).thenReturn(article);
        Article articleTest = articleService.getArticle(article.getId());
        assertEquals(articleTest.getId(), article.getId());
    }

    @Test (expected = InvalidIdException.class)
    public void getArticleByIdNegativeTest() {
        Article articleTest = articleService.getArticle(-1);
    }

    @Test
    public void addArticleTest() {
        when(articleDAO.create(any(Article.class), anySet(), anySet())).thenReturn(article);
        Set<Author> authorSet = mock(Set.class);
        Set<Tag> tagSet = mock(Set.class);
        Article articleTest = articleService.addArticle(new Article(), authorSet, tagSet);
        assertEquals(articleTest.getId(), article.getId());
    }

    @Test (expected = InvalidArticleException.class)
    public void addArticleNegativeTest_NullArticle() {
        Set<Author> authorSet = mock(Set.class);
        Set<Tag> tagSet = mock(Set.class);
        Article articleTest = articleService.addArticle(null, authorSet, tagSet);
    }

    @Test (expected = EmptySetException.class)
    public void addArticleNegativeTest_EmptyAuthorSet() {
        Set<Author> authorSet = new HashSet<Author>();
        Set<Tag> tagSet = mock(Set.class);
        Article articleToAdd = mock(Article.class);
        Article articleTest = articleService.addArticle(articleToAdd, authorSet, tagSet);
    }

    @Test (expected = EmptySetException.class)
    public void addArticleNegativeTest_EmptyTagSet() {
        Set<Author> authorSet = mock(Set.class);
        Set<Tag> tagSet = new HashSet<Tag>();
        Article articleToAdd = mock(Article.class);
        Article articleTest = articleService.addArticle(articleToAdd, authorSet, tagSet);
    }

    @Test (expected = InvalidArticleException.class)
    public void editArticleNegativeTest() {
        articleService.editArticle(null);
    }

    @Test (expected = InvalidArticleException.class)
    public void deleteArticleNegativeTest() {
        articleService.deleteArticle(null);
    }

    @Test
    public void totalNewsCountTest() {
        when(articleDAO.getTotalCount()).thenReturn(articleList.size());
        assertEquals(articleService.totalNewsCount(), articleList.size());
    }
}
