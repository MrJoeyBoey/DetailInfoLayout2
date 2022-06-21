package com.j.detailinfolayout2.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

public class TextViewUtil {

    public static void setDrawableLeft(Context context, TextView textView, int drawableId) {
        Drawable drawable = DrawableUtil.getDrawable(context, drawableId);
        setDrawableLeft(textView, drawable);
    }
    public static void setDrawableLeft(Context context, TextView textView, Bitmap bitmap) {
        Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        setDrawableLeft(textView, drawable);
    }
    public static void setDrawableLeft(TextView textView, Drawable drawable) {
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public static void setDrawableTop(Context context, TextView textView, int drawableId) {
        Drawable drawable = DrawableUtil.getDrawable(context, drawableId);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, drawable, null, null);
        }
    }
    public static void setDrawableTop(Context context, TextView textView, Bitmap bitmap) {
        Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, drawable, null, null);
        }
    }
    public static void setDrawableTop(Context context, TextView textView, int drawableId, int drawableSize) {
        Drawable drawable = DrawableUtil.getDrawable(context, drawableId);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawableSize, drawableSize);
            textView.setCompoundDrawables(null, drawable, null, null);
        }
    }

    public static void setDrawableRight(TextView textView, Drawable drawable) {
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        textView.setCompoundDrawables(null, null, drawable, null);
    }
    public static void setDrawableRight(Context context, TextView textView, int drawableId) {
        Drawable drawable = DrawableUtil.getDrawable(context, drawableId);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        textView.setCompoundDrawables(null, null, drawable, null);
    }
    public static void setDrawableRight(Context context, TextView textView, int drawableId,int drawableSize) {
        Drawable drawable = DrawableUtil.getDrawable(context, drawableId);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawableSize, drawableSize);
            textView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    public static void setDrawableHorizontal(Context context, TextView textView, @DrawableRes int leftDrawableId, @DrawableRes int rightDrawableId) {
        Drawable leftDrawable = DrawableUtil.getDrawable(context, leftDrawableId);
        Drawable rightDrawable = DrawableUtil.getDrawable(context, rightDrawableId);
        if (leftDrawable != null) leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
        if (rightDrawable != null) rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        textView.setCompoundDrawables(leftDrawable, null, rightDrawable, null);
    }

}
