package com.example.matmamob.mvppattern.activities.main;

public class MainPresenter implements MainMVP.Presenter {
    private MainMVP.Model model;
    private MainMVP.View view;

    public MainPresenter(MainMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(MainMVP.View view) {
        this.view = view;
    }

    @Override
    public void setSwipeContainer() {
        if (view != null) {
            view.setActionBar();
            view.setViewPager();
            view.setFragmentRefresh();
        }
    }
}
