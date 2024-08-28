package com.ericsson.MainBackUp.DAO;

import com.ericsson.MainBackUp.CSVProcessor.CSVProcessor;
import com.ericsson.MainBackUp.Model.FileCommit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class FileCommitDAOImpl extends JdbcDaoSupport implements FileCommitDAO
{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    class FileCommitMapper implements RowMapper<FileCommit>{
        @Override
        public FileCommit mapRow(ResultSet rs, int rowNum) throws SQLException {
            FileCommit c = new FileCommit();
            c.setRepo_id(rs.getInt("repo_id"));
            c.setCommit_hash(rs.getString("commit_hash"));
            c.setFiles_in_commit(rs.getInt("files_in_commit"));
            c.setNumber_of_commits(rs.getInt("number_of_commits"));
            c.setDate(LocalDateTime.parse(rs.getString("date")));
            c.setCommitter(rs.getString("committer"));
            c.setFileHash(rs.getString("file_hash"));
            c.setRepo_name(rs.getString("repo_name"));
            c.setFilename(rs.getString("filename"));
            c.setLines_added(rs.getInt("lines_added"));
            c.setLines_removed(rs.getInt("lines_removed"));
            c.setChange_set_avg(rs.getInt("change_set_avg"));
            c.setChange_set_max(rs.getInt("change_set_max"));
            c.setMinor_contributor_count(rs.getInt("minor_contributor_count"));
            c.setContributor_count(rs.getInt("contributor_count"));
            c.setContributor_experience(rs.getDouble("contributor_experience"));
            c.setCode_churn_avg(rs.getInt("code_churn_avg"));
            c.setCode_churn_max(rs.getInt("code_churn_max"));
            c.setHunks_count(rs.getDouble("hunks_count"));


            return c;
        }
    }


    @Override
    public List<FileCommit> getByRepo(int id) {
        List<FileCommit> rows = getJdbcTemplate().query("SELECT * FROM commits WHERE repo_id =?", new FileCommitMapper(), id);
        return rows;
    }

    @Override
    public List<FileCommit> getAll() {
        String sql = "SELECT * FROM filecommits";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<FileCommit> result = new ArrayList<FileCommit>();
        FileCommitMapper fm = new FileCommitMapper();
        for (Map<String, Object> rs : rows) {
            FileCommit c = new FileCommit();
            c.setRepo_id((int)rs.get("repo_id"));
            c.setCommit_hash((String)rs.get("commit_hash"));
            c.setFiles_in_commit((int)rs.get("files_in_commit"));
            c.setNumber_of_commits((int)rs.get("number_of_commits"));
            c.setDate((LocalDateTime)rs.get("date"));
            c.setCommitter((String)rs.get("committer"));
            c.setFileHash((String)rs.get("file_hash"));
            c.setRepo_name((String)rs.get("repo_name"));
            c.setFilename((String)rs.get("filename"));
            c.setLines_added((int)rs.get("lines_added"));
            c.setLines_removed((int)rs.get("lines_removed"));
            c.setChange_set_avg((int)rs.get("change_set_avg"));
            c.setChange_set_max((int)rs.get("change_set_max"));
            c.setMinor_contributor_count((int)rs.get("minor_contributor_count"));
            c.setContributor_count((int)rs.get("contributor_count"));
            c.setContributor_experience((double)rs.get("contributor_experience"));
            c.setCode_churn_avg((int)rs.get("code_churn_avg"));
            c.setCode_churn_max((int)rs.get("code_churn_max"));
            c.setHunks_count((double)rs.get("hunks_count"));

            result.add(c);
        }

        return result;
    }

    @Override
    public FileCommit getByHash(String hash) {
        List<FileCommit> rows = getJdbcTemplate().query("SELECT * FROM filecommits WHERE hash =?", new FileCommitMapper(), hash);
        return rows.get(0);
    }

    @Override
    public void writeCsvToSql()
    {
        CSVProcessor cp = new CSVProcessor();
        try
        {
            cp.initCsvProcessing();
            HashMap<String,String> scheduled = cp.getScheduledCsv();

            //your code to upload to mysql database here

        }catch(Exception e)
        {

        }

    }

    @Override
    public List<FileCommit> getBetweenDate(@RequestParam("/before_date") LocalDateTime before_date, @RequestParam("/after_date") LocalDateTime after_date)
    {
        List<FileCommit> result = new ArrayList<FileCommit>();
        return result;
    }

    public void insertByHash() {
         int num = getJdbcTemplate().update("INSERT INTO filecommits values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
//                 fileCommit.getRepo_id(),
//                 fileCommit.getRepo_name(),
//                 fileCommit.getHash(),
//                 fileCommit.getFilename(),
//                 fileCommit.getLines_added(),
//                 fileCommit.getLines_removed(),
//                 fileCommit.getChange_set_max(),
//                 fileCommit.getChange_set_avg(),
//                 fileCommit.getCode_churn_avg(),
//                 fileCommit.getCode_churn_max(),
//                 fileCommit.getContributor_count(),
//                 fileCommit.getMinor_contributor_count(),
//                 fileCommit.getHunks_count()
                 1,
                 "TEST ",
                 12334534,
                 "FILENAME",
                 100,
                 200,
                 300,
                 400,
                 500,
                 600,
                 700,
                 10,
                 10
         );
        System.out.println("the result isssssssssssssssssssssssssssssssssssssss: "+num);
//        return rows.get(0);
    }
}