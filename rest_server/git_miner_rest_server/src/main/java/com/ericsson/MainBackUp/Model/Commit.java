package com.ericsson.MainBackUp.Model;

import java.time.LocalDateTime;

public class Commit
{
    private long id;
    private String hash;
    private String author;
    private LocalDateTime dateTime;
    private String timezone;
    private int lines;

    public Commit()
    {

    }

    public Commit(long id,String hash, String author, LocalDateTime datetime, String timezone, int lines)
    {
        this.id = id;
        this.hash = hash;
        this.author = author;
        this.dateTime = datetime;
        this.timezone = timezone;
        this.lines = lines;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }
}
