package com.example.matmamob.mvppattern.activities.detail;

import io.realm.Realm;
import io.realm.RealmList;

public interface DetailMVP {
    interface Model {
        void saveIngredient(String ingredient, RealmList<String> ingredientsList);
        void saveListData(int id, RealmList<String> listIngredients);
        RealmList<String> loadData(int id);
        void setRealmHelper(Realm realm);

        void removeElement(RealmList<String> realmList, int position);
    }

    interface View {
        void initialize();
        void blockArchievedUiIfNeeded();
        void setRecyclerView();
    }

    interface Presenter {
        void setView(DetailMVP.View view);
        RealmList<String> provideData(int id);
        void provideSaveList(int id, RealmList<String> listIngredients);
        void provideSaveIngredient(String ingredient, RealmList<String> listIngredients);
        void setRealm(Realm realm);

        void provideRemoveElement(RealmList<String> realmList, int position);
    }
}
