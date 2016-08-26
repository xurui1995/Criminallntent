package com.example.xw.criminallntent;

import android.support.v4.app.Fragment;

/**
 * Created by xw on 2016/8/26.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
