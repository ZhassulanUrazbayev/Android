package com.hfad.storagetask1.Model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DataDAO {
    @Insert
    public void insert(Data... data);

    @Update
    public void update(Data... data);

    @Delete
    public void delete(Data data);

    @Query("SELECT * FROM data")
    public List<Data> getValue();

    @Query("SELECT * FROM data WHERE value = :name")
    public Data getDataWithName(String name);
}
