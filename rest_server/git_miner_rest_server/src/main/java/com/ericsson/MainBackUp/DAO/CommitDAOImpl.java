package com.ericsson.MainBackUp.DAO;

import com.ericsson.MainBackUp.Model.Commit;

import com.ericsson.MainBackUp.Model.FileCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class CommitDAOImpl extends JdbcDaoSupport implements CommitDAO
{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    class CommitMapper implements RowMapper<Commit>{
        @Override
        public Commit mapRow(ResultSet rs, int rowNum) throws SQLException {
            Commit c = new Commit();
            c.setId(rs.getInt("repo_id"));
            c.setHash(rs.getString("hash"));
            c.setAuthor(rs.getString("author"));
            c.setDateTime((LocalDateTime)(rs.getObject("datetime")));
            c.setLines(rs.getInt("code_lines"));
            c.setTimezone(rs.getString("timezone"));
            return c;
        }
    }


    @Override
    public List<Commit> getAll() {
        String sql = "SELECT * FROM commits";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Commit> result = new ArrayList<Commit>();
        for (Map<String, Object> row : rows) {
            Commit c = new Commit();
            c.setId((Integer) row.get("repo_id"));
            c.setHash((String) row.get("hash"));
            c.setAuthor((String) row.get("author"));
            c.setDateTime((LocalDateTime) row.get("datetime"));
            c.setTimezone((String) row.get("timezone"));
            c.setLines((Integer) row.get("code_lines"));
            result.add(c);
        }

        return result;
    }

    @Override
    public List<Commit> getByAuthor(String author) {
        List<Commit> rows = getJdbcTemplate().query("SELECT * FROM commits WHERE author =?", new CommitMapper(), author);
        return rows;
    }

    @Override
    public List<Commit> getByTimeZone(String timezone) {
        List<Commit> rows = getJdbcTemplate().query("SELECT * FROM commits WHERE timezone =?", new CommitMapper(), timezone);
        return rows;
    }

    @Override
    public List<Commit> getBeforeDate(LocalDateTime date) {
        String sql = "SELECT * FROM commits;";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Commit> result = new ArrayList<Commit>();
        for (Map<String, Object> row : rows)
        {
            if (!((LocalDateTime) row.get("datetime")).isBefore(date))
            {
                continue;
            }
            Commit c = new Commit();
            c.setId((Integer) row.get("repo_id"));
            c.setHash((String) row.get("hash"));
            c.setAuthor((String) row.get("author"));
            c.setDateTime((LocalDateTime) row.get("datetime"));
            c.setTimezone((String) row.get("timezone"));
            c.setLines((Integer) row.get("code_lines"));
            result.add(c);
        }
        return result;
    }

    @Override
    public List<Commit> getAfterDate(LocalDateTime date) {
        String sql = "SELECT * FROM commits;";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Commit> result = new ArrayList<Commit>();
        for (Map<String, Object> row : rows)
        {
            if (!((LocalDateTime) row.get("datetime")).isAfter(date))
            {
                continue;
            }
            Commit c = new Commit();
            c.setId((Integer) row.get("repo_id"));
            c.setHash((String) row.get("hash"));
            c.setAuthor((String) row.get("author"));
            c.setDateTime((LocalDateTime) row.get("datetime"));
            c.setTimezone((String) row.get("timezone"));
            c.setLines((Integer) row.get("code_lines"));
            result.add(c);
        }
        return result;
    }



    @Override
    public Commit getById(int id) {
        List<Commit> rows = getJdbcTemplate().query("SELECT * FROM commits WHERE repo_id =?", new CommitMapper(), id);

        return rows.get(0);
    }

    @Override
    public Commit getByHash(String hash) {
        List<Commit> rows = getJdbcTemplate().query("SELECT * FROM commits WHERE hash =?", new CommitMapper(), hash);
        return rows.get(0);
    }


}