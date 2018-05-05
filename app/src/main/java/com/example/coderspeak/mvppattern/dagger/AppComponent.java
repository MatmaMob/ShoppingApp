package com.example.coderspeak.mvppattern.dagger;

import com.example.coderspeak.mvppattern.activities.create.CreateActivity;
import com.example.coderspeak.mvppattern.activities.detail.DetailActivity;
import com.example.coderspeak.mvppattern.activities.main.MainActivity;
import com.example.coderspeak.mvppattern.fragments.Archieved.ArchievedFragment;
import com.example.coderspeak.mvppattern.fragments.Current.CurrentFragment;

import dagger.Component;

@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(DaggApp app);

    void inject(MainActivity mainActivity);

    void inject(CreateActivity createActivity);

    void inject(DetailActivity detailActivity);

    void inject(CurrentFragment currentFragment);

    void inject(ArchievedFragment archievedFragment);
}
