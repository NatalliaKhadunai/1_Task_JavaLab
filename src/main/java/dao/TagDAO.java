package dao;

import entity.Account;
import entity.Tag;

import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public interface TagDAO extends DAO {
    void create(String tag);
    Tag getTag(String name);
    List<Tag> listTags();
    void delete(Tag tag);
}
