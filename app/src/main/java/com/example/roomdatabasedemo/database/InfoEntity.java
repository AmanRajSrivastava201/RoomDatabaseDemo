package com.example.roomdatabasedemo.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import static com.example.roomdatabasedemo.constants.Constants.DATABASE_NAME;

@Entity(tableName = DATABASE_NAME)
public class InfoEntity {

    public InfoEntity() {
    }

    public InfoEntity(String firstname, String surname){
        this.firstname=firstname;
        this.surname=surname;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;

    @ColumnInfo @NonNull
    private String firstname;

    @ColumnInfo @NonNull
    private String surname;

    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NonNull String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }
}
