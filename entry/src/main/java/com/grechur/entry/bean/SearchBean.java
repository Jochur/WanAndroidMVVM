package com.grechur.entry.bean;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SearchBean
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/19 14:03
 */
public class SearchBean {
    private String title;
    private SearchItem item;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SearchItem getItem() {
        return item;
    }

    public void setItem(SearchItem item) {
        this.item = item;
    }

    public static class SearchItem{

        private List<String> history;
        private List<HotBean> hot;

        public List<String> getHistory() {
            return history;
        }

        public void setHistory(List<String> history) {
            this.history = history;
        }

        public List<HotBean> getHot() {
            return hot;
        }

        public void setHot(List<HotBean> hot) {
            this.hot = hot;
        }
    }
}
