package com.grechur.collect.paging;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;


/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ConcertDao
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/13 12:51
 */
@Dao
public interface ConcertDao {
    @Query("SELECT * FROM concerts ORDER BY date DESC")
    DataSource.Factory<Integer, Concert> concertsByDate();

    @Insert
    void insertDate(Concert concert);
}
