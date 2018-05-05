package com.example.coderspeak.mvppattern.db;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.coderspeak.mvppattern.db.data.ListIngredients;
import com.example.coderspeak.mvppattern.db.data.ShoppingList;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmHelper {

    private final Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void closeRealm() {
        realm.close();
    }

    public void setArchievedElement(final boolean isArchieved, final int id) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                ShoppingList results = realm.where(ShoppingList.class).equalTo("id", id).findFirst();
                results.setArchieved(isArchieved);
            }
        });
    }

    public List<ShoppingList> getAllArchievedShoppingLists() {
        final List<ShoppingList> list = new ArrayList<>();
        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(@NonNull Realm realm) {
                RealmResults<ShoppingList> results = realm.where(ShoppingList.class).equalTo("isArchieved", true).findAllAsync();
                results.load();
                for (ShoppingList s : results) {
                    ShoppingList sl = new ShoppingList();
                    sl.setTime(s.getTime());
                    sl.setArchieved(s.isArchieved());
                    sl.setTitle(s.getTitle());
                    sl.setId(s.getId());
                    list.add(sl);
                }
            }
        });
        return list;
    }

    public List<ShoppingList> getAllCurrentShoppingLists() {
        final List<ShoppingList> list = new ArrayList<>();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ShoppingList> results = realm.where(ShoppingList.class).findAllAsync();
                results.load();
                for (ShoppingList s : results) {
                    ShoppingList sl = new ShoppingList();
                    sl.setTime(s.getTime());
                    sl.setArchieved(s.isArchieved());
                    sl.setTitle(s.getTitle());
                    sl.setId(s.getId());
                    list.add(sl);
                }
            }
        });
        return list;
    }

    public void saveShoppingList(final SharedPreferences sharedPreferences, final String title, final Long time) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ShoppingList sl = realm.createObject(ShoppingList.class, sharedPreferences.getInt("id", 0));
                sl.setArchieved(false);
                sl.setTitle(title);
                sl.setTime(time);
            }
        });
    }

    public void saveIngedient(final String ingredient, final RealmList<String> ingredientsList) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ingredientsList.add(ingredient);
            }
        });
    }

    public void saveIngredientList(final int id, final RealmList<String> listIngredients) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ListIngredients sli = realm.createObject(ListIngredients.class);
                sli.setItemsList(listIngredients);
                sli.setListId(String.valueOf(id));
            }
        });
    }

    public RealmList<String> getIngredient(int id) {
        ListIngredients sli = realm.where(ListIngredients.class).equalTo("listId", String.valueOf(id)).findFirst();
        RealmList<String> list;
        if (sli != null) {
            list = sli.getItemsList();
        } else {
            list = new RealmList<>();
        }
        return list;
    }
}
