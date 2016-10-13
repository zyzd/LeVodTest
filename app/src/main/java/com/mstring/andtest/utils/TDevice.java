package com.mstring.andtest.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.mstring.andtest.ApiApplication;

/**
 * Created by 李宗源 on 2016/8/1.
 */
public class TDevice {

    public static float dpToPixel(float dp) {
        return dp * (getDisplayMetrics().densityDpi / 160F);
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) ApiApplication.getContext().getSystemService(
                Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
                displaymetrics);
        return displaymetrics;
    }

    public static float ptToPixel(float sp){
       return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getDisplayMetrics());

    }



    /**
     * 掩藏软键盘
     *
     * @param view
     */
    public static void hideSoftKeyboard(View view) {
        if (view == null)
            return;
        View focusView = null;
        if (view instanceof EditText)//判断view是否是EditText
            focusView = view;
        Context context = view.getContext();
        if (context != null && context instanceof Activity) {//context得是Activity
            Activity activity = ((Activity) context);
            focusView = activity.getCurrentFocus();//获取当前activity中获得焦点的view
        }

        if (focusView != null) {//当前activity中有获得焦点的view，才进行掩藏软键盘
                /*
                if (focusView.isFocusable()) {
                    focusView.setFocusable(false);
                    focusView.setFocusable(true);
                }
                */
            if (focusView.isFocused()) {//取消获得焦点view的获取状态
                focusView.clearFocus();
            }
            //掩藏软键盘
            InputMethodManager manager = (InputMethodManager) focusView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(focusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            manager.hideSoftInputFromInputMethod(focusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 对话框显示软键盘
     *
     * @param dialog
     */
    public static void showSoftKeyboard(Dialog dialog) {
        dialog.getWindow().setSoftInputMode(4);
    }

    /**
     * view显示软键盘
     *
     * @param view
     */
    public static void showSoftKeyboard(View view) {
        if (view == null)
            return;

        //当view没有获得焦点就令其获取焦点
        if (!view.isFocusable())
            view.setFocusable(true);
        if (!view.isFocusableInTouchMode())
            view.setFocusableInTouchMode(true);
        if (!view.isFocused()) {
            view.requestFocus();
        }
        //显示软键盘
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
        inputMethodManager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
    }

    /**
     * 切换软键盘的显示状态
     *
     * @param view
     */
    public static void toogleSoftKeyboard(View view) {
        ((InputMethodManager) ApiApplication.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
