package com.ericsson.MainBackUp.DAO;

import com.ericsson.MainBackUp.Model.Commit;
import org.apache.tomcat.jni.Local;


import java.time.LocalDateTime;
import java.util.List;


public interface CommitDAO
{
    List<Commit> getAll();
    List<Commit> getByAuthor(String author);
    List<Commit> getByTimeZone(String timezone);

    List<Commit> getBeforeDate(LocalDateTime date);
    List<Commit> getAfterDate(LocalDateTime date);



    Commit getById(int id);
    Commit getByHash(String hash);




}