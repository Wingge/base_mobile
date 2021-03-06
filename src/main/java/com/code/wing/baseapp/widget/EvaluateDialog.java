package com.code.wing.baseapp.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * 可返回数据的fragment dialog,参考了http://blog.csdn.net/lmj623565791/article/details/42628537
 * 该类使用的关键是setTargetFragment，如果没
 */
public class EvaluateDialog extends DialogFragment {
    private static final int NON_REQUEST_CODE = 980;
    private static int mRequestCode;
    AlertDialog.Builder alertDialogBuilder;

    /**
     * 需要设置dialog
     * @param alertDialogBuilder
     */
    public void setAlertDialogBuilder(AlertDialog.Builder alertDialogBuilder) {
        this.alertDialogBuilder = alertDialogBuilder;
    }

    public static EvaluateDialog newInstance() {
        return newInstance(NON_REQUEST_CODE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(alertDialogBuilder!=null){
            return alertDialogBuilder.create();
        }else{
            throw new RuntimeException("you must using instance.setAlertDialogBuilder method to create the dialog view");
        }
    }

    public static EvaluateDialog newInstance(int requestCode) {
        mRequestCode = requestCode;
        EvaluateDialog fragment = new EvaluateDialog();
        return fragment;
    }

    /**
     * 设置返回数据
     * @param bundle 数据
     */
    protected void setResult(Bundle bundle) {
        // 判断是否设置了targetFragment
        if (getTargetFragment() == null)
            return;
        Intent intent = new Intent();
        intent.putExtras(bundle);
        getTargetFragment().onActivityResult(mRequestCode, Activity.RESULT_OK, intent);
    }

}
