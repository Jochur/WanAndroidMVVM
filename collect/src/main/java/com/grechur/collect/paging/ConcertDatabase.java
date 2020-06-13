package com.grechur.collect.paging;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ConsertDatabase
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/13 14:25
 */
@Database(entities = {Concert.class}, version = 1)
public abstract class ConcertDatabase extends RoomDatabase {

    private static volatile ConcertDatabase instance;

    public static ConcertDatabase getInstance(Context context){
        if(instance == null){
            synchronized (ConcertDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ConcertDatabase.class,
                            "concert.db")
                            .allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }

    public abstract ConcertDao concertDao();

}
