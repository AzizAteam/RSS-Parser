package com.google.codelabs.mdc.java.shrine.repository;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import retrofit2.http.Url;

@Dao
public interface WebsitesDAO {
    @Query("SELECT * FROM websites where id = :id")
    LiveData<List<Url>> getItem (Long id);

    @Insert
    long insertItem(Url url);

    @Update
    int updateItem(Url url);

    @Query("DELETE FROM websites WHERE id = :id")
    int deleteItem(long id);
}
