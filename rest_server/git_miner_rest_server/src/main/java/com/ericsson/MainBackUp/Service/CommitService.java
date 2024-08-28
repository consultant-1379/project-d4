package com.ericsson.MainBackUp.Service;

import com.ericsson.MainBackUp.Model.Commit;


import java.time.LocalDateTime;
import java.util.List;


public interface CommitService
{
    List<Commit> getAllCommit();
    List<Commit> getByAuthor(String author);
    List<Commit> getByTimeZone(String timezone);

    List<Commit> getBeforeDate(LocalDateTime date);
    List<Commit> getAfterDate(LocalDateTime date);

    Commit getById(int id);
    Commit getByHash(String hash);



}