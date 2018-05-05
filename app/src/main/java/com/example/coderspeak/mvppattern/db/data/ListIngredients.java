package com.example.coderspeak.mvppattern.db.data;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ListIngredients extends RealmObject {
    private String listId;
    private RealmList<String> itemsList;

    public ListIngredients() {
    }

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
