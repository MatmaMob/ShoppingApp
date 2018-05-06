package com.example.matmamob.mvppattern.fragments.Archieved;

import com.example.matmamob.mvppattern.db.data.ShoppingList;

import java.util.List;

import io.realm.Realm;

public interface ArchievedMVP {
    interface Model {
        List<ShoppingList> loadShoppingListData();

        void setArchievedElement(boolean isArchieved, int id);

        void close(Realm realm);

        void setRealmHelper(Realm realm);
    }

    interface View {
        void initialize();

        void setRecyclerView();

    }

    interface Presenter {
        void setView(ArchievedMVP.View view);

        List<ShoppingList> provideData(Realm realm);

        void setRealm(Realm realm);

        void setAdapterData(boolean isArchieved, int id);

        void closeDatabase(Realm realm);
    }
}
