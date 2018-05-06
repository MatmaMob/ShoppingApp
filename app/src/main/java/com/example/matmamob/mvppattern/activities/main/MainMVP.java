package com.example.matmamob.mvppattern.activities.main;

public interface MainMVP {
    interface Model {

    }

    interface View {
        void initialize();
        void setViewPager();
        void setActionBar();
        void setFragmentRefresh();
    }

    interface Presenter {
        void setView(MainMVP.View view);
        void setSwipeContainer();
    }
}
