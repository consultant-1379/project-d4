package com.ericsson.MainBackUp.Controllers;

import com.ericsson.MainBackUp.Model.Commit;
import com.ericsson.MainBackUp.Service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@CrossOrigin
public class CommitController {

    @Autowired
    CommitService commitService;

    @RequestMapping(value = "/")
    public String testRoot()
    {
        return "Root is working";
    }


    @RequestMapping(value = "/get_all", method = RequestMethod.GET)
    public List<Commit> getAll()
    {
        return commitService.getAllCommit();
    }

    @RequestMapping(value = "/get_by_id", method = RequestMethod.GET)
    public Commit getById(@RequestParam("repo_id") int id)
    {
        return commitService.getById(id);
    }

    @RequestMapping(value = "/get_by_hash", method = RequestMethod.GET)
    public Commit getHash(@RequestParam("hash") String hash) {

        return commitService.getByHash(hash);

    }

    @RequestMapping(value = "/get_by_author", method = RequestMethod.GET)
    public List<Commit> getByAuthor(@RequestParam("author") String author) {

        return commitService.getByAuthor(author);

    }

    @RequestMapping(value = "/get_after_date", method = RequestMethod.GET)
    public List<Commit> getAfterDate(@RequestParam("date") String date)
    {
        return commitService.getAfterDate(LocalDateTime.parse(date));
    }

    @RequestMapping(value = "/get_before_date", method = RequestMethod.GET)
    public List<Commit> getBeforeDate(@RequestParam("date") String date)
    {
        return commitService.getBeforeDate(LocalDateTime.parse(date));
    }



}