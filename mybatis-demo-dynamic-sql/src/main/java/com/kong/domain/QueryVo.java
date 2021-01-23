package com.kong.domain;

import java.util.List;

public class QueryVo {

    private User user;
    //用户列表
    private List<Integer> userids;

    public List<Integer> getUserids() {
        return userids;
    }

    public void setUserids(List<Integer> userids) {
        this.userids = userids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
