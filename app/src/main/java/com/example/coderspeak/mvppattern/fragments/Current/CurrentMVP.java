package com.example.coderspeak.mvppattern.fragments.Current;

import com.example.coderspeak.mvppattern.db.data.ShoppingList;

import java.util.List;

import io.realm.Realm;

public interface CurrentMVP {
    interface Model {
        List<ShoppingList> loadCurrentShoppingLists();

        void setArchievedElement(boolean isArchieved, int id);

        void setRealmHelper(Realm realm);

        void close(Realm realm);
    }

    interface View {
        void initialize();

        void setRecyclerView();
    }

    interface Presenter {
        void setView(CurrentMVP.View view);

        void setRealm(Realm realm);

        List<ShoppingList> provideData();

        void setAdapterData(boolean isArchieved, int id);

        void closeDatabase(Realm realm);
    }
}
