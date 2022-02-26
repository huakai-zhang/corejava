package com.spring.model;

public class Author {

    private int aid;

    private String authorName;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "aid=" + aid +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
