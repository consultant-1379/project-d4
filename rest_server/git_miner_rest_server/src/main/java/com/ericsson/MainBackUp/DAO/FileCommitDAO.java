package com.ericsson.MainBackUp.DAO;

import com.ericsson.MainBackUp.Model.FileCommit;


import java.time.LocalDateTime;
import java.util.List;


public interface FileCommitDAO
{
    List<FileCommit> getByRepo(int id);
    List<FileCommit> getAll();
    List<FileCommit> getBetweenDate(LocalDateTime before_date,LocalDateTime after_date);
    FileCommit getByHash(String hash);
    void insertByHash();
    void writeCsvToSql();



}