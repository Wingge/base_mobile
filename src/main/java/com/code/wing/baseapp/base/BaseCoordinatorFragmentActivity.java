package com.code.wing.baseapp.base;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.code.wing.baseapp.R;

public abstract class BaseCoordinatorFragmentActivity extends BaseFragmentActivity {

//    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
//    @BindView(R.id.action_button)
    FloatingActionButton action_button;
//    @BindView(R.id.backdrop)
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
        action_button = (FloatingActionButton) findViewById(R.id.action_button);
        action_button = (FloatingActionButton) findViewById(R.id.action_button);
        loadBackdrop();
        super.initToolbar();
    }

    /**
     * 获取连接按钮，如果不需要请返回-1
     *
     * @return
     */
    abstract int getBackdropActionImage();

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
        return R.layout.activity_coordinator_base;
    }

}