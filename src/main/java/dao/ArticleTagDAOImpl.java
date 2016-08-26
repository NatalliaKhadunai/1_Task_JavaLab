package dao;

import entity.Article;
import entity.Tag;
import manager.SQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by Natallia_Khadunai on 8/26/2016.
 */
public class ArticleTagDAOImpl implements ArticleTagDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void attachTag(int articleId, int tagId) {
        String SQL = SQLQueryManager.getProperty("Article_Tag.addRow");
        jdbcTemplateObject.update(SQL, articleId, tagId);
    }

    public List<Integer> searchByTags(Set<String> tagIdSet) {
        String SQL = SQLQueryManager.getProperty("Article_Tag.searchByTags");
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("tags", tagIdSet);
        RowMapper<Integer> rowMapper = new RowMapper<Integer>() {
            public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                return resultSet.getInt("article_id");
            }
        };
        List<Integer> articleIdList = jdbcTemplateObject.query(SQL, rowMapper, parameters);
        return articleIdList;
    }
}
