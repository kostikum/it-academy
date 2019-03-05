package com.kostikum.itac.dz4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.kostikum.itac.R;

public class Dz4PagerActivity extends FragmentActivity {

    PieChartFragment mPieChartFragment;
    ClockFragment mClockFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz4_pager);

        mPieChartFragment = new PieChartFragment();
        mClockFragment = new ClockFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewPager viewPager = findViewById(R.id.activity_dz4_pager_view_pager);
        viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                return i == 1 ? mPieChartFragment : mClockFragment;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }
}
