package com.abhiyaan.androidapp.vocabjournal.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

/**
 * Created by Binaya Bhattarai on 3/25/2018.
 */

public class WordViewPagerAdapter extends FragmentStatePagerAdapter{
    private String[] tabTitles = {"Definition", "Usage Examples"};

    public WordViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new WordDefinitionFragment();
        }
        return new SentencesListFragment();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
