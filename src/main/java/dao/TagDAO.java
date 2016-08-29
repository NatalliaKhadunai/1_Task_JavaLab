package dao;

import entity.Account;
import entity.Tag;

import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public interface TagDAO extends DAO {
    Tag create(Tag tag);
    Tag getTag(int id);
    List<Tag> listTags();
    void delete(Tag tag);
}
