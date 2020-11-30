package com.turnkeyafrica.bankassurance.ui.dashboard;

import com.turnkeyafrica.bankassurance.ui.dashboard.claims.ClaimsFragment;
import com.turnkeyafrica.bankassurance.ui.dashboard.policy.PolicyFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

public class DashboardPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;

    public DashboardPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount = 2;
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {

        if(position == 0){return PolicyFragment.newInstance();}else {return ClaimsFragment.newInstance();}

    }
}
