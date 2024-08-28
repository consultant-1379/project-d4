package com.ericsson.MainBackUp.Repo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repo")
public class RepoController
{

    @RequestMapping("/")
    public String getRoot()
    {
        return "/repo root is working";
    }


}
