package com.example.matmamob.mvppattern.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.matmamob.mvppattern.R;
import com.example.matmamob.mvppattern.activities.create.CreateActivity;
import com.example.matmamob.mvppattern.adapters.CustomViewPagerAdapter;
import com.example.matmamob.mvppattern.dagger.DaggApp;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainMVP.View, ActionBar.TabListener {
    @Inject
    MainMVP.Presenter presenter;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ActionBar actionBar;
    private CustomViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((DaggApp) getApplication()).getAppComponent().inject(this);
        initialize();
        presenter.setSwipeContainer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.setView(null);
    }

    @Override
    public void initialize() {
        presenter.setView(this);
    }

    @Override
    public void setViewPager() {
        viewPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void setActionBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            ActionBar.Tab currentTab = actionBar.newTab();
            currentTab.setText("Current");
            currentTab.setTabListener(this);

            ActionBar.Tab archievedTab = actionBar.newTab();
            archievedTab.setText("Archieved");
            archievedTab.setTabListener(this);

            actionBar.addTab(currentTab);
            actionBar.addTab(archievedTab);
        }
    }

    @Override
    public void setFragmentRefresh() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
                Fragment f = viewPagerAdapter.getFragment(position);

                if (f != null) {
                    f.onResume();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addList:
                startActivity(new Intent(MainActivity.this, CreateActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
    }
}
