package com.example.coderspeak.mvppattern.fragments.Archieved;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coderspeak.mvppattern.R;
import com.example.coderspeak.mvppattern.adapters.ArchievedRecyclerAdapter;
import com.example.coderspeak.mvppattern.dagger.DaggApp;
import com.example.coderspeak.mvppattern.db.data.ShoppingList;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArchievedFragment extends Fragment implements ArchievedMVP.View {
    @Inject
    ArchievedMVP.Presenter presenter;

    @Inject
    Realm realm;

    @Inject
    SharedPreferences sharedPreferences;

    @BindView(R.id.archievedRecyclerView)
    RecyclerView archievedRecyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private List<ShoppingList> shoppingList;
    private ArchievedRecyclerAdapter adapter;

    public ArchievedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_archieved, container, false);
        ButterKnife.bind(this, v);
        ((DaggApp) getActivity().getApplication()).getAppComponent().inject(this);
        initialize();
        setRecyclerView();
        return v;
    }

    @Override
    public void initialize() {
        presenter.setView(this);
        presenter.setRealm(realm);
        shoppingList = presenter.provideData(realm);
    }

    @Override
    public void setRecyclerView() {
        Collections.sort(shoppingList);
        adapter = new ArchievedRecyclerAdapter(getContext(), shoppingList, realm, true, presenter);
        layoutManager = new LinearLayoutManager(getContext());
        archievedRecyclerView.setLayoutManager(layoutManager);
        archievedRecyclerView.setItemAnimator(new DefaultItemAnimator());
        archievedRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.setView(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        shoppingList = presenter.provideData(realm);
        setRecyclerView();
    }
}
