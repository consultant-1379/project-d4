package com.ericsson.MainBackUp.Service;

import com.ericsson.MainBackUp.Model.FileCommit;


import java.io.File;
import java.time.LocalDateTime;
import java.util.List;


public interface FileCommitService
{
    FileCommit getByHash(String hash);
    List<FileCommit> getByRepo(int id);
    List<FileCommit> getAll();
    List<FileCommit> getBetweenDate(LocalDateTime before_date, LocalDateTime after_date);
    void insertByHash();
    void writeCsvToSql();

}