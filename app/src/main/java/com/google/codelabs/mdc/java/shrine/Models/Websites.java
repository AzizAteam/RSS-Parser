package com.google.codelabs.mdc.java.shrine.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "websites")
public class Websites {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "url")
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Websites(int id, String url) {
        this.id = id;
        this.url = url;
    }
}

