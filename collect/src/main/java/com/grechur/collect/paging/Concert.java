package com.grechur.collect.paging;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: Concert
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/12 18:20
 */
@Entity(tableName = "concerts")
public class Concert {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phone;
    private long date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
