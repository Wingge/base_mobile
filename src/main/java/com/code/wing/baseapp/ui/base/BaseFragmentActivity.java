package com.code.wing.baseapp.ui.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.code.wing.baseapp.R;

import butterknife.ButterKnife;

public abstract class BaseFragmentActivity extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();
    Toolbar mToolbar;
    //ContentView
    protected ViewGroup view_content;

    protected abstract void initViews(Bundle savedInstanceState);
    /**用于子类定义在oncreate中需实现的代码*/
    protected void initOther(Bundle savedInstanceState){}

    /*标识是否可返回栈*/
    boolean mCanBackStack = true;

    FragmentManager mFragmentManager;

    //初始fragment，或还原fragment
    protected abstract Fragment initOrRestoreFragment(Bundle savedInstanceState);

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        view_content = (ViewGroup) findViewById(R.id.main_content);
        initToolbar();
        mFragmentManager = getSupportFragmentManager();
        Fragment fragment = mFragmentManager.findFragmentById(getFragmentContentId());
        if (fragment == null) {
            fragment = initOrRestoreFragment(savedInstanceState);
            if (fragment != null)
                mFragmentManager.beginTransaction().add(getFragmentContentId(), fragment).commit();
        }
        initOther(savedInstanceState);
        initViews(savedInstanceState);
    }

    /**
     * show mToolbar
     * if the title is set ,the title will show else it will show the label set to the activity
     */
    protected void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            if (showNavigationIcon()) {
//            mToolbar.setNavigationIcon(R.mipmap.toolbar_back);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                mToolbar.setSubtitle("副标题");
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
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
     *
     * @return fragment layout Id
     */
    public int getContentViewId() {
        return R.layout.activity_base;
    }

    /**
     * default layout is base_fragment,you can overide it
     *
     * @return the activity content fragment id
     */
    public int getFragmentContentId() {
        return R.id.id_fragment_container;
    }

    /**
     * set true to show navigation icon
     *
     * @return navigation icon status
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
     *
     * @param fragment
     */
    protected void addFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName());
            if (mCanBackStack) {
                fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            }
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    /**
     * 添加fragment,并检查实现已经存在,如果是将会重用实例避免重复实例产生
     *
     * @param fragment
     */
    protected void addFragmentAndCheckCase(Fragment fragment) {
        if (fragment != null) {
            Fragment oldFragment = mFragmentManager.findFragmentByTag(fragment.getClass().getSimpleName());
            if (oldFragment != null) {
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                /*以下的两个方法暂不考虑优劣 */
//                    fragmentTransaction.hide(mFragmentManager.);
//                    fragmentTransaction.show(oldFragment);
                fragmentTransaction.attach(oldFragment);
                fragmentTransaction.commit();

            } else {
                addFragment(fragment);
            }
        }
    }

    /**
     * /移除fragment
     */
    protected void removeFragment() {
//        if (mFragmentManager.getBackStackEntryCount() > 1) {
//            mFragmentManager.popBackStack();
//        } else {
//            finish();
//        }
        onBackPressed();
    }

    /**
     * 设置当前activity包含的fragment是否可以使用返回栈
     * @param canBackStack
     */
    public void setCanBackStack(boolean canBackStack) {
        mCanBackStack = canBackStack;
    }

    public void startActivity(Class<?> c,Bundle bundle){
        Intent intent = new Intent(this,c);
        if(bundle!=null)
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startActivity(Class<?> c){
        startActivity(c,null);
    }


    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

}