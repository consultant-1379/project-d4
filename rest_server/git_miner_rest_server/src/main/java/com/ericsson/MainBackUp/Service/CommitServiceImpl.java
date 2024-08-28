package com.ericsson.MainBackUp.Service;

import com.ericsson.MainBackUp.DAO.CommitDAO;
import com.ericsson.MainBackUp.Model.Commit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CommitServiceImpl implements CommitService {

    @Autowired
    CommitDAO commitDao;

    public List<Commit> getAllCommit() {
        List<Commit> commits = commitDao.getAll();
        return commits;
    }

    @Override
    public List<Commit> getByAuthor(String author) {
        List<Commit> commits = commitDao.getByAuthor(author);
        return commits;
    }

    @Override
    public List<Commit> getByTimeZone(String timezone) {
        List<Commit> commits = commitDao.getByTimeZone(timezone);
        return commits;
    }

    @Override
    public List<Commit> getBeforeDate(LocalDateTime date) {
        List<Commit> commits = commitDao.getBeforeDate(date);
        return commits;
    }

    @Override
    public List<Commit> getAfterDate(LocalDateTime date) {
        List<Commit> commits = commitDao.getAfterDate(date);
        return commits;
    }

    @Override
    public Commit getById(int id) {
        Commit commit = commitDao.getById(id);
        return commit;
    }

    @Override
    public Commit getByHash(String hash) {
        Commit commit = commitDao.getByHash(hash);
        return commit;
    }




}