package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SampleAdapter extends FragmentStateAdapter {


    public SampleAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            FirstFragment fragment1 = new FirstFragment();
            return fragment1;
        } else {
            SecondFragment fragment2 = new SecondFragment();
            return fragment2;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
