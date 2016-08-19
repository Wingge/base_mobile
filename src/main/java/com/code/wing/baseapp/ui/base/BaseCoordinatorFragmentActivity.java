package com.code.wing.baseapp.ui.base;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.code.wing.baseapp.R;
import com.code.wing.baseapp.util.MDStatusBarCompat;

public abstract class BaseCoordinatorFragmentActivity extends BaseFragmentActivity {

    CollapsingToolbarLayout collapsing_toolbar;
    AppBarLayout appbar;
    FloatingActionButton action_button;
    ImageView backdrop;


    @Override
    public void setTitle(CharSequence title) {
        collapsing_toolbar.setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        collapsing_toolbar.setTitle(getString(titleId));
    }


    @Override
    protected void initToolbar() {
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        action_button = (FloatingActionButton) findViewById(R.id.action_button);
        backdrop = (ImageView) findViewById(R.id.backdrop);
        loadBackdrop();
        super.initToolbar();
        MDStatusBarCompat.setCollapsingToolbar(this, (CoordinatorLayout) view_content, appbar, backdrop, mToolbar);

    }

    /**
     * 获取连接按钮，如果不需要请返回-1
     *
     * @return image resourse id
     */
    protected abstract int getBackdropActionImage();

    private void loadBackdrop() {
        int backdropImage = getBackdropActionImage();
        if (backdropImage == -1) {
            backdrop.setVisibility(View.GONE);
        } else {
            Glide.with(this).load(backdropImage).centerCrop().into(backdrop);
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_coordinator_nestedscroll_base;
    }

}