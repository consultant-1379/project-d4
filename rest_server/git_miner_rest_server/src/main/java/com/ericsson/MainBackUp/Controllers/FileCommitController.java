package com.ericsson.MainBackUp.Controllers;

import com.ericsson.MainBackUp.Model.FileCommit;
import com.ericsson.MainBackUp.Service.FileCommitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/filecommit")
public class FileCommitController {

    @Autowired
    FileCommitService fileCommitService;

    @RequestMapping(value = "/")
    public String testRoot()
    {
        return "Mapping the main -> backup csv";
    }


    @RequestMapping(value = "/get_all", method = RequestMethod.GET)
    public List<FileCommit> getAll()
    {
        return fileCommitService.getAll();
    }

    @RequestMapping(value = "/get_by_hash", method = RequestMethod.GET)
    public FileCommit getHash(@RequestParam("hash") String hash) {
        return fileCommitService.getByHash(hash);
    }

    @RequestMapping(value = "/get_by_repo", method = RequestMethod.GET)
    public List<FileCommit> getByRepo(@RequestParam("id") int id)
    {
        return fileCommitService.getByRepo(id);
    }


    @RequestMapping(value = "/write_csv", method = RequestMethod.GET)
    public String writeCsv()
    {
        fileCommitService.writeCsvToSql();
        return "Writing";
    }

    @RequestMapping(value = "/get_between_date", method = RequestMethod.GET)
    public List<FileCommit> getBetween(@RequestParam("before_date") String before_date, @RequestParam("after_date") String after_date)
    {
        return fileCommitService.getBetweenDate(LocalDateTime.parse(before_date),LocalDateTime.parse(after_date));
    }


}