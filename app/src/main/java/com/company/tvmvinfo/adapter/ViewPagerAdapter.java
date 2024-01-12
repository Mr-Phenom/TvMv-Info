package com.company.tvmvinfo.adapter;

import static com.company.tvmvinfo.activity.SearchActivity.name;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.company.tvmvinfo.fragment.MovieFragmentSearch;
import com.company.tvmvinfo.fragment.TvFragmentSearch;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position)
        {
            case 0:
            {
                fragment= MovieFragmentSearch.getInstance(name);
                break;
            }

            case 1:
            {
                fragment = TvFragmentSearch.getInstance(name);
                break;
            }

            default:
                return null;

        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

