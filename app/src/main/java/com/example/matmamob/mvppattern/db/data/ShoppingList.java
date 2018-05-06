package com.example.matmamob.mvppattern.db.data;

import android.support.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ShoppingList extends RealmObject implements Comparable<ShoppingList> {
    @PrimaryKey
    private int id;
    private String title;
    private boolean isArchieved;
    private long time;

    public ShoppingList() {
    }

    public ShoppingList(int id, String title, boolean isArchieved) {
        this.id = id;
        this.title = title;
        this.isArchieved = isArchieved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isArchieved() {
        return isArchieved;
    }

    public void setArchieved(boolean archieved) {
        isArchieved = archieved;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int compareTo(@NonNull ShoppingList o) {
        int compareTime = (int) o.getTime();
        return (int) (this.time - compareTime);
    }
}
