package com.grechur.entry.bean;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: NavigationInfo
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/12 18:16
 */
public class NavigationInfo {
    private List<ArticleInfo> articles;
    private int cid;
    private String name;

    public List<ArticleInfo> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleInfo> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
