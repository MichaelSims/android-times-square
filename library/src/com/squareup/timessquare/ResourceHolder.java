package com.squareup.timessquare;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class ResourceHolder {

    public Drawable activeDrawable;
    public Drawable inactiveDrawable;
    public Drawable confirmedDrawable;
    public Drawable unconfirmedDrawable;
    public Drawable todayDrawable;
    public Drawable selectedDrawable;

    public ResourceHolder(Context context) {
        activeDrawable = context.getResources().getDrawable(R.drawable.active_selector);
        inactiveDrawable = context.getResources().getDrawable(R.drawable.inactive_selector);
        confirmedDrawable = context.getResources().getDrawable(R.drawable.confirmed_selector);
        unconfirmedDrawable = context.getResources().getDrawable(R.drawable.unconfirmed_selector);
        todayDrawable = context.getResources().getDrawable(R.drawable.today_overlay);
        selectedDrawable = context.getResources().getDrawable(R.drawable.selected);
    }
}
