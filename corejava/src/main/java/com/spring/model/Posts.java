package com.spring.model;

public class Posts {

    private int pid;

    private String postName;

    private int bid;

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "pid=" + pid +
                ", postName='" + postName + '\'' +
                ", bid=" + bid +
                '}';
    }
}
