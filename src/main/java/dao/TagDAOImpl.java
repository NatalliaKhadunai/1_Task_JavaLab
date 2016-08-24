package dao;

import entity.Account;
import entity.Tag;
import manager.SQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public class TagDAOImpl implements TagDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(String tag) {
        String SQL = SQLQueryManager.getProperty("TagDAO.addTag");
        jdbcTemplateObject.update( SQL, tag);
    }

    public Tag getTag(String name) {
        String SQL = SQLQueryManager.getProperty("TagDAO.getTag");
        Tag tag = jdbcTemplateObject.queryForObject(SQL, new TagMapper(), name);
        return tag;
    }

    public List<Tag> listTags() {
        String SQL = SQLQueryManager.getProperty("TagDAO.getAllTags");
        List<Tag> tagList = jdbcTemplateObject.query(SQL, new TagMapper());
        return tagList;
    }

    public void delete(Tag tag){
        String SQL = SQLQueryManager.getProperty("TagDAO.deleteTag");
        jdbcTemplateObject.update(SQL, tag.getId());
    }
}
