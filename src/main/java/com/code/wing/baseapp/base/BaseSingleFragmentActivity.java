package com.code.wing.baseapp.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * TODO
 * 参考：http://blog.csdn.net/lmj623565791/article/details/42628537
 * Created by wing on 16/1/24.
 */
public abstract class BaseSingleFragmentActivity extends BaseFragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(getFragmentContentId());

        if (fragment == null) {
            fragment = initOrRestoreFragment(savedInstanceState);

            fm.beginTransaction().add(getFragmentContentId(), fragment).commit();
        }
    }

    /**
     * SingleFragmentActivity , so...no need addFragmentAndCheckCase
     *
     * @param fragment
     * @deprecated
     */
    @Override
    protected void addFragmentAndCheckCase(Fragment fragment) {

    }
}
