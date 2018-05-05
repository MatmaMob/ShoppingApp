package com.example.coderspeak.mvppattern.activities.create;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.coderspeak.mvppattern.R;
import com.example.coderspeak.mvppattern.dagger.DaggApp;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class CreateActivity extends AppCompatActivity implements CreateMVP.View {
    @Inject
    CreateMVP.Presenter presenter;

    @Inject
    Realm realm;

    @Inject
    SharedPreferences sharedPreferences;

    @BindView(R.id.createTitleInput)
    EditText createListInput;

    @OnClick(R.id.saveListBtn)
    public void saveList() {
        presenter.provideData(createListInput.getText().toString());
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ((DaggApp) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        presenter.setRealm(realm);
        presenter.provideSharedPreferences(sharedPreferences);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.setView(null);
        presenter.closeDatabase(realm);
    }
}
