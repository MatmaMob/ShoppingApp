package com.example.coderspeak.mvppattern.activities.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.coderspeak.mvppattern.R;
import com.example.coderspeak.mvppattern.adapters.DetailRecyclerAdapter;
import com.example.coderspeak.mvppattern.dagger.DaggApp;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;

public class DetailActivity extends AppCompatActivity implements DetailMVP.View {
    @Inject
    DetailMVP.Presenter presenter;

    @Inject
    Realm realm;

    private Intent intent;
    private boolean isArchieved;
    private int id;

    private RecyclerView.LayoutManager layoutManager;
    private DetailRecyclerAdapter adapter;

    private RealmList<String> ingredientsList;

    @BindView(R.id.listIdText)
    TextView listIdText;
    @BindView(R.id.detailRecyclerView)
    RecyclerView detailRecyclerView;
    @BindView(R.id.ingredientInput)
    EditText ingredientInput;
    @BindView(R.id.saveList)
    Button saveBtn;

    @BindView(R.id.saveIngredientBtn)
    Button saveIngredientBtn;

    @OnClick(R.id.saveList)
    public void saveList() {
        presenter.provideSaveList(id, ingredientsList);
        finish();
    }

    @OnClick(R.id.saveIngredientBtn)
    public void saveIngredient() {
        presenter.provideSaveIngredient(ingredientInput.getText().toString(), ingredientsList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ((DaggApp) getApplication()).getAppComponent().inject(this);
        hideKeyboard();
        presenter.setRealm(realm);
        ingredientsList = presenter.provideData(id);

        intent = getIntent();
        isArchieved = intent.getBooleanExtra("isArchieved", false);
        if (isArchieved) {
            ingredientInput.setVisibility(View.GONE);
            saveBtn.setVisibility(View.GONE);
            saveIngredientBtn.setVisibility(View.GONE);
        }
        id = intent.getIntExtra("id", 0);

        initialize();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.setView(null);
    }

    @Override
    public void initialize() {
        presenter.setView(this);
        blockArchievedUiIfNeeded();
        listIdText.setText(String.valueOf(id));
        setRecyclerView();
    }

    @Override
    public void blockArchievedUiIfNeeded() {
        if (isArchieved) {
            saveIngredientBtn.setClickable(false);
            saveBtn.setClickable(false);
        } else {
            saveIngredientBtn.setClickable(true);
            saveBtn.setClickable(true);
        }
    }

    @Override
    public void setRecyclerView() {
        adapter = new DetailRecyclerAdapter(ingredientsList, realm, isArchieved);
        layoutManager = new LinearLayoutManager(this);
        detailRecyclerView.setLayoutManager(layoutManager);
        detailRecyclerView.setItemAnimator(new DefaultItemAnimator());
        detailRecyclerView.setAdapter(adapter);
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromInputMethod(ingredientInput.getWindowToken(), 0);
    }


}
