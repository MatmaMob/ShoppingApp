package com.example.coderspeak.mvppattern.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.coderspeak.mvppattern.fragments.Archieved.ArchievedFragment;
import com.example.coderspeak.mvppattern.fragments.Current.CurrentFragment;

import java.util.HashMap;

public class CustomViewPagerAdapter extends FragmentPagerAdapter {
    private HashMap<Integer, String> fragmentsTag = new HashMap<>();
    private FragmentManager fm;

    public CustomViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;

        switch (position) {
            case 0:
                f = new CurrentFragment();
                break;

            case 1:
                f = new ArchievedFragment();
                break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object o = super.instantiateItem(container, position);
        if (o instanceof Fragment) {
            Fragment f = (Fragment) o;
            String tag = f.getTag();
            fragmentsTag.put(position, tag);
        }
        return o;
    }

    public Fragment getFragment(int position) {
        Fragment f = new Fragment();
        String tag = fragmentsTag.get(position);
        if (tag != null) {
            f = fm.findFragmentByTag(tag);
        }
        return f;
    }
}
