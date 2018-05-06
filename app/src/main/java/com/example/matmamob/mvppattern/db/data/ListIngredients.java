package com.example.matmamob.mvppattern.db.data;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ListIngredients extends RealmObject {

    private String listId;
    private RealmList<String> itemsList;

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public RealmList<String> getItemsList() {
        return itemsList;
    }

    public void setItemsList(RealmList<String> itemsList) {
        this.itemsList = itemsList;
    }
}
