package com.example.coderspeak.mvppattern.activities.detail;

import io.realm.Realm;
import io.realm.RealmList;

public interface DetailMVP {
    interface Model {
        void saveIngredient(String ingredient, RealmList<String> ingredientsList);

        void saveListData(int id, RealmList<String> listIngredients);

        RealmList<String> loadData(int id);

        void setRealmHelper(Realm realm);

        void close(Realm realm);
    }

    interface View {
        void initialize();

        void blockArchievedUiIfNeeded();

        void setRecyclerView();

        void hideKeyboard();
    }

    interface Presenter {
        void setView(DetailMVP.View view);

        RealmList<String> provideData(int id);

        void provideSaveList(int id, RealmList<String> listIngredients);

        void provideSaveIngredient(String ingredient, RealmList<String> listIngredients);

        void closeDatabase(Realm realm);

        void setRealm(Realm realm);
    }
}
