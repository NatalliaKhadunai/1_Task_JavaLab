package service;

import entity.Article;
import entity.Tag;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
public interface TagService {
    Tag createTag(Tag tag);
    void attachTag(Tag tag, Article article);
    List<Article> searchByTags(Set<Tag> tagSet);
    Map<Tag, Integer> totalNewsCountForEachTag();
}
