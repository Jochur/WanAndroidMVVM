package com.grechur.entry.bean;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: RankInfo
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/19 20:02
 */
public class RankInfo {
//    {
//        "coinCount": 14399,
//            "level": 144,
//            "rank": "1",
//            "userId": 20382,
//            "username": "g**eii"
//    },
    private long coinCount;
    private int level;
    private String rank;
    private long userId;
    private String username;

    public long getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(long coinCount) {
        this.coinCount = coinCount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
