package com.example.coderspeak.mvppattern.dagger;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.coderspeak.mvppattern.fragments.Archieved.ArchievedMVP;
import com.example.coderspeak.mvppattern.fragments.Archieved.ArchievedModel;
import com.example.coderspeak.mvppattern.fragments.Archieved.ArchievedPresenter;
import com.example.coderspeak.mvppattern.activities.create.CreateMVP;
import com.example.coderspeak.mvppattern.activities.create.CreateModel;
import com.example.coderspeak.mvppattern.activities.create.CreatePresenter;
import com.example.coderspeak.mvppattern.activities.detail.DetailMVP;
import com.example.coderspeak.mvppattern.activities.detail.DetailModel;
import com.example.coderspeak.mvppattern.activities.detail.DetailPresenter;
import com.example.coderspeak.mvppattern.activities.main.MainMVP;
import com.example.coderspeak.mvppattern.activities.main.MainModel;
import com.example.coderspeak.mvppattern.activities.main.MainPresenter;
import com.example.coderspeak.mvppattern.fragments.Current.CurrentMVP;
import com.example.coderspeak.mvppattern.fragments.Current.CurrentModel;
import com.example.coderspeak.mvppattern.fragments.Current.CurrentPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class AppModule {
    private final DaggApp app;

    public AppModule(DaggApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return app;
    }

    @Provides
    SharedPreferences providesSharePreferences() {
        return app.getSharedPreferences("id_pref", Context.MODE_PRIVATE);
    }

    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    MainMVP.Presenter provideMainPresenter(MainMVP.Model model) {
        return new MainPresenter(model);
    }

    @Provides
    MainMVP.Model provideMainModel() {
        return new MainModel();
    }

    @Provides
    CreateMVP.Presenter provideCreatePresenter(CreateMVP.Model model) {
        return new CreatePresenter(model);
    }

    @Provides
    CreateMVP.Model provideCreateModel() {
        return new CreateModel();
    }

    @Provides
    DetailMVP.Presenter provideDetailPresenter(DetailMVP.Model model) {
        return new DetailPresenter(model);
    }

    @Provides
    DetailMVP.Model provideDetailModel() {
        return new DetailModel();
    }

    @Provides
    ArchievedMVP.Presenter provideArchievedPresenter(ArchievedMVP.Model model) {
        return new ArchievedPresenter(model);
    }

    @Provides
    ArchievedMVP.Model provideArchievedModel() {
        return new ArchievedModel();
    }

    @Provides
    CurrentMVP.Presenter provideCurrentPresenter(CurrentMVP.Model model) {
        return new CurrentPresenter(model);
    }

    @Provides
    CurrentMVP.Model provideCurrentModel() {
        return new CurrentModel();
    }
}
