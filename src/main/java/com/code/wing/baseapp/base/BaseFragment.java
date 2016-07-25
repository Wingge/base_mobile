package com.code.wing.baseapp.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;

//import butterknife.ButterKnife;

/**
 * @date 2016/6/21 0021 10:55
 * @author Wing.Zhong
 * @Description TODO
 * @version ccode
 */
public abstract class BaseFragment  extends Fragment {
    protected final String TAG = getClass().getSimpleName();
    public BaseFragmentActivity mActivity;
    private static final String STATE_SAVE_IS_HIDDEN = "state_save_is_hidden";
    private CharSequence mTitle ;
    private CharSequence mSubTitle ;
    //传入的参数
    protected Bundle args;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            args= getArguments();
        }

        if (savedInstanceState != null) {
            initStatusByHideOrShow(savedInstanceState);
        }
        mTitle = this.getClass().getSimpleName();
    }

    /**
     * @date 2016/6/21 0021 13:48
     * @author Wing.Zhong
     * @description 用于避免重叠fragment，参考了http://www.jianshu.com/p/c12a98a36b2b
     * @version V0.0.1
     * @param savedInstanceState 保存的数据
     * @return void
     */
    private void initStatusByHideOrShow(Bundle savedInstanceState) {
        boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (isSupportHidden) {
            ft.hide(this);
        } else {
            ft.show(this);
        }
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        /**默认副标题为空*/
        setSubTitle(null);
        initViews(rootView, savedInstanceState);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract void initViews(View view, Bundle savedInstanceState);

    //获取fragment布局文件ID
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected BaseFragmentActivity getHoldingActivity() {
        return mActivity;
    }

    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (null != fragment&&isParentAlive()) {
            mActivity.addFragmentAndCheckCase(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        if(isParentAlive()) mActivity.removeFragment();
    }

    /*
* onAttach(Context) is not called on pre API 23 versions of Android and onAttach(Activity) is deprecated
* Use onAttachToContext instead
*/
    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isParentAlive()) {
            //为空表示不想显示title
            if (mTitle != null) {
                mActivity.setTitle(mTitle);
            }
            mActivity.getSupportActionBar().setSubtitle(mSubTitle);
        }
    }

    /*
                 * Deprecated on API 23
                 * Use onAttachToContext instead
                 */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /*
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        this.mActivity = context instanceof BaseFragmentActivity ? (BaseFragmentActivity) context : null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //用于避免重叠fragment，参考了http://www.jianshu.com/p/c12a98a36b2b
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    /**
     * @date 2016/6/28 0028 17:07
     * @author Wing.Zhong
     * @description 设置标题
     * @param
     * @return 
     */
    protected void setTitle(CharSequence title){
        mTitle = title;
    }

    /**
     * 设置副标题，默认为空，可重写，之所以写这个方法是因为在多个fragment中，一旦有一个fragment设置了副标题，其它的fragment也会同时出现一样的副标题
     * @param title
     */
    public void setSubTitle(CharSequence title){
        mSubTitle = title;
    }

    public void startActivity(Class<?> c,Bundle bundle){
        Intent intent = new Intent(getContext(),c);
        if(bundle!=null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startActivity(Class<?> c){
        startActivity(c,null);
    }

    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent(getContext(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    private boolean isParentAlive(){
        return mActivity!=null&&!(mActivity.isFinishing());
    }

}
