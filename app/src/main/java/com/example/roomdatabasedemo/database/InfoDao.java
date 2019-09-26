package com.example.roomdatabasedemo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static com.example.roomdatabasedemo.constants.Constants.DATABASE_NAME;

@Dao
public interface InfoDao {
    @Insert
    public void insert(InfoEntity... myentity);

    @Query("Select * from " + DATABASE_NAME + "")
    public LiveData<List<InfoEntity>> getdata();

    @Query("DELETE FROM " + DATABASE_NAME + " WHERE id = :eid")
    public int deleteByID(int eid);

    @Query("Update " + DATABASE_NAME + " SET firstname=:mfirstname ,surname=:mlastname  WHERE id=:mid")
    public void update(String mfirstname, String mlastname, int mid);

}

