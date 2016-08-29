package dao;

import entity.Tag;

import java.util.List;
import java.util.Set;

/**
 * Created by Natallia_Khadunai on 8/26/2016.
 */
public interface ArticleTagDAO extends DAO {
    void attachTag(int articleId, int tagId);
    List<Integer> searchByTags(Set<Tag> tagSet);
    List<Integer> searchByTags(Tag ... tags);
}
