package com.google.codelabs.mdc.java.shrine.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "favourites")
public class Favourites {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="author")
    private String author;
    @ColumnInfo(name="image")
    private String image;
    @ColumnInfo(name="title")
    private String title;
    @ColumnInfo(name="contentr")
    private String content;
    @ColumnInfo(name="logo")
    private String logo;
    @ColumnInfo(name = "date")
    private Date date;

    public Favourites(int id, String author, String logo, String image, String title, String content, Date date) {
        this.id = id;
        this.author = author;
        this.logo = logo;
        this.image = image;
        this.title = title;
        this.content = content;
        this.date = date;
    }
    public Favourites() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

