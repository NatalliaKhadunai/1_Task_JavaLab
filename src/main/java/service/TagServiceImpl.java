package service;

import com.sun.xml.internal.ws.developer.Serialization;
import dao.ArticleDAO;
import dao.ArticleTagDAO;
import dao.TagDAO;
import entity.Article;
import entity.Tag;
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

    public Tag createTag(Tag tag) {
        return tagDAO.create(tag);
    }

    public void attachTag(Tag tag, Article article) {
        articleTagDAO.attachTag(article.getId(), tag.getId());
    }

    public List<Article> searchByTags(Set<Tag> tagSet) {
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
            articlesPerTag.put(tag, articleTagDAO.searchByTags(tag).size());
        }
        return articlesPerTag;
    }
}
