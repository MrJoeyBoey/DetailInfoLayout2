package com.j.detailinfolayout2.utils;

import android.view.View;
import android.view.ViewGroup;

import com.j.mediaview.utils.ToastUtil;

public class ViewUtil {

    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime = 0;

    public static void setMarginStart(View view, int start){
        setMarginStart(view, start,false);
    }
    public static void setMarginStart(View view, int start, boolean keepOtherMargin){
        ViewGroup.MarginLayoutParams mlp = getMarginLayoutParams(view);
        if (keepOtherMargin) {
            mlp.setMargins(start, mlp.topMargin, mlp.rightMargin, mlp.bottomMargin);
        } else {
            mlp.setMargins(start, 0, 0, 0);
        }
        view.setLayoutParams(mlp);
    }

    public static void setMarginEnd(View view, int end){
        setMarginEnd(view, end,false);
    }
    public static void setMarginEnd(View view, int end, boolean keepOtherMargin){
        ViewGroup.MarginLayoutParams mlp = getMarginLayoutParams(view);
        if (keepOtherMargin) {
            mlp.setMargins(mlp.leftMargin, mlp.topMargin, end, mlp.bottomMargin);
        } else {
            mlp.setMargins(0, 0, end, 0);
        }
        view.setLayoutParams(mlp);
    }

    public static void setMarginTop(View view, int top){
        setMarginTop(view, top,false);
    }
    public static void setMarginTop(View view, int top, boolean keepOtherMargin){
        ViewGroup.MarginLayoutParams mlp = getMarginLayoutParams(view);
        if (keepOtherMargin) {
            mlp.setMargins(mlp.leftMargin, top, mlp.rightMargin, mlp.bottomMargin);
        } else {
            mlp.setMargins(0, top, 0, 0);
        }
        view.setLayoutParams(mlp);
    }

    public static void setMarginBottom(View view, int bottom){
        setMarginBottom(view, bottom,false);
    }
    public static void setMarginBottom(View view, int bottom, boolean keepOtherMargin){
        ViewGroup.MarginLayoutParams mlp = getMarginLayoutParams(view);
        if (keepOtherMargin) {
            mlp.setMargins(mlp.leftMargin, mlp.topMargin, mlp.rightMargin, bottom);
        } else {
            mlp.setMargins(0, 0, 0, bottom);
        }
        view.setLayoutParams(mlp);
    }

    public static void setMargin(View view, int left , int top, int right, int bottom){
        ViewGroup.MarginLayoutParams mlp = getMarginLayoutParams(view);
        mlp.setMargins(left, top, right, bottom);
        view.setLayoutParams(mlp);
    }

    public static ViewGroup.MarginLayoutParams getMarginLayoutParams(View view){
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        ViewGroup.MarginLayoutParams mlp;
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            mlp = (ViewGroup.MarginLayoutParams) lp;
        } else {
            mlp = new ViewGroup.MarginLayoutParams(lp);
        }
        return mlp;
    }

    public static void setPaddingTop(View view, int top){
        setPaddingTop(view, top,false);
    }
    public static void setPaddingTop(View view, int top, boolean keepOtherPadding){
        if (keepOtherPadding) {
            view.setPadding(view.getPaddingStart(), top, view.getPaddingEnd(), view.getPaddingBottom());
        } else {
            view.setPadding(0, top, 0, 0);
        }
    }

    public static void setViewHeight(View view, int height){
        setViewHeight(view, height, false);
    }

    public static void setViewHeight(View view, int height, boolean accumulation){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = accumulation ? params.height + height : height;
        view.requestLayout();
    }

    public static void setViewWidth(View view, int width){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        view.requestLayout();
    }

    public static void setViewSize(View view, int width, int height){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.requestLayout();
    }

    public static int getViewHeight(View view){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        return params.height;
    }

    public static int getViewWidth(View view){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        return params.width;
    }


    private static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        long result = curClickTime - lastClickTime;
        if (result <= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    public static void setOnClickListener(View view, View.OnClickListener onClickListener){
        view.setOnClickListener(v -> {
            if (!isFastClick()){
                onClickListener.onClick(v);
            } else {
                ToastUtil.showShort(view.getContext(), "点击过快,请稍后再试");
            }
        });
    }

}
