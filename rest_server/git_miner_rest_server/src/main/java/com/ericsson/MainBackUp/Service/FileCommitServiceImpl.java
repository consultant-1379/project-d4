package com.ericsson.MainBackUp.Service;

import com.ericsson.MainBackUp.DAO.FileCommitDAO;
import com.ericsson.MainBackUp.Model.FileCommit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class FileCommitServiceImpl implements FileCommitService {

    @Autowired
    FileCommitDAO fileCommitDao;

    @Override
    public FileCommit getByHash(String hash) {
        return fileCommitDao.getByHash(hash);
    }

    @Override
    public List<FileCommit> getByRepo(int id) {
        return fileCommitDao.getByRepo(id);
    }

    @Override
    public List<FileCommit> getAll() {
        return fileCommitDao.getAll();
    }

    @Override
    public void insertByHash()
    {
        fileCommitDao.insertByHash();
    }

    @Override
    public void writeCsvToSql()
    {
        fileCommitDao.writeCsvToSql();
    }

    @Override
    public List<FileCommit> getBetweenDate(LocalDateTime before_date, LocalDateTime after_date)
    {
        return fileCommitDao.getBetweenDate(before_date,after_date);
    }




}