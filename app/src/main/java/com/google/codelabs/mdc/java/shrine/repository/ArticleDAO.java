package com.google.codelabs.mdc.java.shrine.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.codelabs.mdc.java.shrine.Models.Article;

import java.util.List;

@Dao
public interface ArticleDAO {
    @Query("SELECT * FROM Article where id = :id")
    LiveData<List<Article>> getItems (Long id);

    @Insert
    long insertItem(Article article);

    @Update
    int updateItem(Article article);

    @Query("DELETE FROM Article WHERE id = :id")
    int deleteItem(long id);
}
