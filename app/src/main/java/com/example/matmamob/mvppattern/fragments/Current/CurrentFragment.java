package com.example.matmamob.mvppattern.fragments.Current;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.matmamob.mvppattern.R;
import com.example.matmamob.mvppattern.adapters.CurrentRecyclerAdapter;
import com.example.matmamob.mvppattern.dagger.DaggApp;
import com.example.matmamob.mvppattern.db.data.ShoppingList;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentFragment extends Fragment implements CurrentMVP.View {
    @Inject
    CurrentMVP.Presenter presenter;

    @Inject
    Realm realm;

    @Inject
    SharedPreferences sharedPreferences;

    @BindView(R.id.currentRecyclerView)
    RecyclerView currentRecyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private CurrentRecyclerAdapter adapter;

    private List<ShoppingList> shoppingList;

    public CurrentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_current, container, false);
        ButterKnife.bind(this, v);
        ((DaggApp) getActivity().getApplication()).getAppComponent().inject(this);
        initialize();
        presenter.provideData();
        setRecyclerView();
        return v;
    }

    @Override
    public void initialize() {
        presenter.setView(this);
        presenter.setRealm(realm);
        shoppingList = presenter.provideData();
    }

    @Override
    public void setRecyclerView() {
        Collections.sort(shoppingList);
        adapter = new CurrentRecyclerAdapter(getContext(), shoppingList, false, presenter);
        layoutManager = new LinearLayoutManager(getContext());
        currentRecyclerView.setLayoutManager(layoutManager);
        currentRecyclerView.setItemAnimator(new DefaultItemAnimator());
        currentRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        shoppingList = presenter.provideData();
        setRecyclerView();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.setView(null);
    }
}
