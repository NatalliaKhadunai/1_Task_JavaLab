package service;

import dao.CommentDAO;
import entity.Comment;
import exception.InvalidCommentException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class CommentServiceImplTest {
    CommentServiceImpl commentService;
    CommentDAO commentDAO;
    Comment comment;

    @Before
    public void setUp() throws Exception {
        commentDAO = mock(CommentDAO.class);
        commentService = new CommentServiceImpl();
        commentService.setCommentDAO(commentDAO);
        comment = new Comment();
        comment.setContent("TEST");
    }

    @Test
    public void addCommentTest() {
        when(commentDAO.create(any(Comment.class))).thenReturn(comment);
        Comment commentTest = commentService.addComment(new Comment());
        assertEquals(commentTest, comment);
    }

    @Test (expected = InvalidCommentException.class)
    public void addCommentNegativeTest() {
        commentService.addComment(null);
    }

    @Test
    public void commentListByArticleIdTest() {
        when(commentDAO.listCommentByArticleId(anyInt())).thenReturn(new ArrayList<Comment>() {
            {
                add(comment);
            }
        });
        List<Comment> commentList = commentService.listCommentByArticleId(1);
        assertTrue(commentList.size() > 0);
    }

    @Test
    public void commentListByAccountLoginTest() {
        when(commentDAO.listCommentByAccountLogin(anyString())).thenReturn(new ArrayList<Comment>() {
            {
                add(comment);
            }
        });
        List<Comment> commentList = commentService.listCommentByAccountLogin("one");
        assertTrue(commentList.size() > 0);
    }

    @Test (expected = InvalidCommentException.class)
    public void deleteCommentNegativeTest() {
        commentService.delete(null);
    }
}
