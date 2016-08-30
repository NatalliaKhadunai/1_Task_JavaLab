package service;

import dao.ArticleDAO;
import dao.ArticleTagDAO;
import dao.TagDAO;
import entity.Article;
import entity.Tag;
import exception.EmptySetException;
import exception.InvalidArticleException;
import exception.InvalidIdException;
import exception.InvalidTagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDAO tagDAO;
    @Autowired
    ArticleTagDAO articleTagDAO;
    @Autowired
    ArticleDAO articleDAO;

    public void setTagDAO(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    public void setArticleTagDAO(ArticleTagDAO articleTagDAO) {
        this.articleTagDAO = articleTagDAO;
    }

    public void setArticleDAO(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public Tag createTag(Tag tag) {
        if (tag != null) return tagDAO.create(tag);
        else throw new InvalidTagException();
    }

    public Tag getTag(int id) {
        if (id > 0) return tagDAO.getTag(id);
        else throw new InvalidIdException("Id should be more than 0");
    }

    public List<Tag> listTags() {
        return tagDAO.listTags();
    }

    public void deleteTag(Tag tag) {
        if (tag == null) throw new InvalidTagException("Tag shouldn't be null");
        if (tag.getId() <= 0) throw new InvalidIdException("Id should be more than 0");
        else tagDAO.delete(tag);
    }

    public void attachTag(Tag tag, Article article) {
        if (tag == null) throw new InvalidTagException("Tag shouldn't be null");
        if (tag.getId() <= 0) throw new InvalidIdException("Tag Id should be more than 0");
        if (article == null) throw new InvalidArticleException("Article shoyldn't be null");
        if (article.getId() <= 0) throw new InvalidIdException("Artice Id should be more than 0");
        articleTagDAO.attachTag(article.getId(), tag.getId());
    }

    public List<Article> searchByTags(Set<Tag> tagSet) {
        if (tagSet == null || tagSet.isEmpty()) throw new EmptySetException("Set<Tag> shouldn't be empty");
        List<Integer> articleIdList = articleTagDAO.searchByTags(tagSet);
        List<Article> articleList = new ArrayList<Article>();
        for (int id : articleIdList) {
            articleList.add(articleDAO.getArticleById(id));
        }
        return articleList;
    }

    public Map<Tag, Integer> totalNewsCountForEachTag() {
        List<Tag> tagList = tagDAO.listTags();
        Map<Tag, Integer> articlesPerTag = new HashMap<Tag, Integer>();
        for (Tag tag : tagList) {
            articlesPerTag.put(tag, articleTagDAO.searchByTag(tag).size());
        }
        return articlesPerTag;
    }
}
