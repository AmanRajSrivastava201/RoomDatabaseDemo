package com.example.roomdatabasedemo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import static com.example.roomdatabasedemo.constants.Constants.DATABASE_NAME;

@Database(entities = {InfoEntity.class},version = 1)
public abstract class InfoDatabase extends RoomDatabase {

    public abstract InfoDao infodao();

    public static InfoDatabase databaseinstance;

    public static InfoDatabase getInstance(Context context){
        if (databaseinstance==null){
            databaseinstance =

                    Room.databaseBuilder(context, InfoDatabase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return databaseinstance;
    }
}
