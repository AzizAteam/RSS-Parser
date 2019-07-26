package com.google.codelabs.mdc.java.shrine.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.codelabs.mdc.java.shrine.Models.Article;

import java.util.List;

@Dao
public interface FavouritesDAO {
    @Query("SELECT * FROM favourites where id = :id")
    LiveData<List<Article>> getItems (Long id);

    @Insert
    long insertItem(Article article);

    @Update
    int updateItem(Article article);

    @Query("DELETE FROM favourites WHERE id = :id")
    int deleteItem(long id);
}
