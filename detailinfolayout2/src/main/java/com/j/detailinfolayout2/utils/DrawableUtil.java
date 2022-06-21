package com.j.detailinfolayout2.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import androidx.core.content.ContextCompat;

public class DrawableUtil {

    public static Drawable getDrawable(Context context, int drawableId){
        return drawableId == 0 ? null : ContextCompat.getDrawable(context, drawableId);
    }

    public static Drawable createShape(int color, int radius){
        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setShape(GradientDrawable.RECTANGLE);
        g.setCornerRadius(radius);
        return g;
    }

    public static Drawable createShape(int color, float[] radius, int strokeWidth, int strokeColor){
        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setShape(GradientDrawable.RECTANGLE);
        g.setCornerRadii(radius);
        g.setStroke(strokeWidth, strokeColor);
        return g;
    }

    public static Drawable createCircle(int color, int diameter, int radius){
        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setSize(diameter, diameter);
        g.setShape(GradientDrawable.OVAL);
        g.setCornerRadius(radius);
        return g;
    }

    public static Drawable createStroke(int strokeWidth, int strokeColor, int radius){
        GradientDrawable g = new GradientDrawable();
        g.setStroke(strokeWidth,strokeColor);
        g.setShape(GradientDrawable.RECTANGLE);
        g.setCornerRadius(radius);
        return g;
    }

    public static StateListDrawable createSelector(Drawable normalDraw,Drawable pressedDraw) {
        StateListDrawable stateListDrawable  = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, pressedDraw);
        stateListDrawable.addState(new int[]{}, normalDraw);
        return stateListDrawable;
    }

    public static StateListDrawable createPress(Drawable normalDraw,Drawable pressedDraw) {
        StateListDrawable stateListDrawable  = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDraw);
        stateListDrawable.addState(new int[]{}, normalDraw);
        return stateListDrawable;
    }

}
