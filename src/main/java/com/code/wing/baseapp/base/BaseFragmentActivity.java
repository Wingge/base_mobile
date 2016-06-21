package com.code.wing.baseapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.code.wing.baseapp.R;

import butterknife.ButterKnife;

public abstract class BaseFragmentActivity extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();
    protected Toolbar toolbar;
    protected abstract void initViews(Bundle savedInstanceState);
    FragmentManager fm;
    //初始fragment，或还原fragment
    protected abstract Fragment initOrRestoreFragment(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initToolbar();
        fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(getFragmentContentId());
        if (fragment == null) {
            fragment = initOrRestoreFragment(savedInstanceState);
            fm.beginTransaction().add(getFragmentContentId(), fragment).commit();
        }

        initViews(savedInstanceState);
    }

    /**
     * show toolbar
     * if the title is set ,the title will show else it will show the label set to the activity
     */
    protected void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (showNavigationIcon()) {
//            toolbar.setNavigationIcon(R.mipmap.toolbar_back);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                toolbar.setSubtitle("副标题");
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        }
    }

    /**
     * 获取布局中Fragment的ID,you can overide it
     * @return
     */
    public int getContentViewId() {
        return R.layout.activity_base;
    }

    /**
     * default layout is base_fragment,you can overide it
     * @return
     */
    protected int getFragmentContentId(){
        return R.id.id_fragment_container;
    }

    /**
     * set true to show navigation icon
     *
     * @return
     */
    protected boolean showNavigationIcon() {
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            hideNavigationBar();
//        }
    }

    private void hideNavigationBar() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    /**
     * Detects and toggles immersive mode (also known as "hidey bar" mode).
     */
    public void toggleHideyBar() {

    }

    /**
     * 添加fragment
     * @param fragment
     */
    protected void addFragment(Fragment fragment) {
        if (fragment != null) {
            fm.beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    /**
     * /移除fragment
     */
    protected void removeFragment() {
//        if (fm.getBackStackEntryCount() > 1) {
//            fm.popBackStack();
//        } else {
//            finish();
//        }
        onBackPressed();
    }


}