package com.code.wing.baseapp.base;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.code.wing.baseapp.R;


public abstract class BaseNavigationFragmentActivity extends BaseFragmentActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawer_layout;
    protected NavigationView nav_view;

    @Override
    protected void initOther(Bundle savedInstanceState) {
        //侧边栏
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.inflateHeaderView(getNavigationHeaderViewResId());
        nav_view.getMenu().clear(); //clear old inflated items.
        nav_view.inflateMenu(getNavigationMenuViewResId());
        nav_view.setNavigationItemSelectedListener(this);

//        nav_view.setItemIconTintList(null);//如果navigation菜单的图标不要求全局一至，的彩色icon就要使用这行代码

        initNavigationViews();

        ActionBarDrawerToggle mDrawerToggle;
//        drawer_layout.setDrawerShadow(R.mipmap.ic_launcher, GravityCompat.START);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer_layout, mToolbar,R.string.menu_open, R.string.menu_close);
//        drawer_layout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        drawer_layout.bringToFront();
        drawer_layout.requestLayout();

    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
//        MDStatusBarCompat.setDrawerToolbar(this,drawer_layout);
    }

    /**给navigation view header view设置布局id*/
    public abstract int getNavigationHeaderViewResId();
    /**给navigation view menu view设置布局id*/
    public abstract int getNavigationMenuViewResId();
    /**实现navigation view item处理*/
    public abstract boolean handleMenuSelected(MenuItem item);
    /**给navigation view设置attr,event等*/
    public abstract void initNavigationViews();


    @Override
    public int getContentViewId() {
        return R.layout.activity_navigation_base;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer_layout.closeDrawers();
        return handleMenuSelected(item);
    }
}