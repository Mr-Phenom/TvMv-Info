package com.company.tvmvinfo.adapter;


import static com.company.tvmvinfo.activity.ClickMoreActivity.category;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.company.tvmvinfo.fragment.MovieFragmentClick;

import com.company.tvmvinfo.fragment.TvFragmentClick;


public class ViewPagerForClick extends FragmentStateAdapter {

    public ViewPagerForClick(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
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
                fragment= MovieFragmentClick.getInstance(category);
                break;
            }

            case 1:
            {
                fragment = TvFragmentClick.getInstance(category);
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
