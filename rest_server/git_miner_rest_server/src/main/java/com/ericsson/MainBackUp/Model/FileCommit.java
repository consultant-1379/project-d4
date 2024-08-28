package com.ericsson.MainBackUp.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FileCommit
{

    private int repo_id;

    private String commit_hash;
    private LocalDateTime date;
    private String committer;
    private int files_in_commit;
    private int number_of_commits;
    private String file_hash;
    private String repo_name;
    private String filename;
    private int lines_added;
    private int lines_removed;
    private int change_set_max;
    private int change_set_avg;
    private int contributor_count;
    private int minor_contributor_count;
    private double contributor_experience;
    private int code_churn_avg;
    private int code_churn_max;
    private double hunks_count;




    public FileCommit()
    {

    }

    public FileCommit(int repo_id, String commit_hash, LocalDateTime date, String committer, int files_in_commit, int number_of_commits, String file_hash, String repo_name, String filename, int lines_added, int lines_removed, int change_set_max, int change_set_avg, int contributor_count, int minor_contributor_count, double contributor_experience,int code_churn_avg, int code_churn_max, double hunks_count) {
        this.repo_id = repo_id;
        this.commit_hash = commit_hash;
        this.date = date;
        this.committer = committer;
        this.files_in_commit = files_in_commit;
        this.number_of_commits = number_of_commits;
        this.file_hash = file_hash;
        this.repo_name = repo_name;
        this.filename = filename;
        this.lines_added = lines_added;
        this.lines_removed = lines_removed;
        this.change_set_max = change_set_max;
        this.change_set_avg = change_set_avg;
        this.code_churn_avg = code_churn_avg;
        this.code_churn_max = code_churn_max;
        this.contributor_count = contributor_count;
        this.minor_contributor_count = minor_contributor_count;
        this.contributor_experience = contributor_experience;
        this.hunks_count = hunks_count;
    }

    public int getRepo_id() {
        return repo_id;
    }

    public void setRepo_id(int repo_id) {
        this.repo_id = repo_id;
    }

    public String getRepo_name() {
        return repo_name;
    }

    public void setRepo_name(String repo_name) {
        this.repo_name = repo_name;
    }

    public String getFileHash() {
        return file_hash;
    }

    public void setFileHash(String hash) {
        this.file_hash = hash;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getLines_added() {
        return lines_added;
    }

    public void setLines_added(int lines_added) {
        this.lines_added = lines_added;
    }

    public int getLines_removed() {
        return lines_removed;
    }

    public void setLines_removed(int lines_removed) {
        this.lines_removed = lines_removed;
    }

    public int getChange_set_max() {
        return change_set_max;
    }

    public void setChange_set_max(int change_set_max) {
        this.change_set_max = change_set_max;
    }

    public int getChange_set_avg() {
        return change_set_avg;
    }

    public void setChange_set_avg(int change_set_avg) {
        this.change_set_avg = change_set_avg;
    }

    public int getCode_churn_avg() {
        return code_churn_avg;
    }

    public void setCode_churn_avg(int code_churn_avg) {
        this.code_churn_avg = code_churn_avg;
    }

    public int getCode_churn_max() {
        return code_churn_max;
    }

    public void setCode_churn_max(int code_churn_max) {
        this.code_churn_max = code_churn_max;
    }

    public int getContributor_count() {
        return contributor_count;
    }

    public void setContributor_count(int contributor_count) {
        this.contributor_count = contributor_count;
    }

    public int getMinor_contributor_count() {
        return minor_contributor_count;
    }

    public void setMinor_contributor_count(int minor_contributor_count) {
        this.minor_contributor_count = minor_contributor_count;
    }

    public double getHunks_count() {
        return hunks_count;
    }

    public void setHunks_count(double hunks_count) {
        this.hunks_count = hunks_count;
    }

    public String getCommit_hash() {
        return commit_hash;
    }

    public void setCommit_hash(String commit_hash) {
        this.commit_hash = commit_hash;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCommitter() {
        return committer;
    }

    public void setCommitter(String committer) {
        this.committer = committer;
    }

    public int getFiles_in_commit() {
        return files_in_commit;
    }

    public void setFiles_in_commit(int files_in_commit) {
        this.files_in_commit = files_in_commit;
    }

    public int getNumber_of_commits() {
        return number_of_commits;
    }

    public void setNumber_of_commits(int number_of_commits) {
        this.number_of_commits = number_of_commits;
    }

    public String getFile_hash() {
        return file_hash;
    }

    public void setFile_hash(String file_hash) {
        this.file_hash = file_hash;
    }

    public double getContributor_experience() {
        return contributor_experience;
    }

    public void setContributor_experience(double contributor_experience) {
        this.contributor_experience = contributor_experience;
    }
}
