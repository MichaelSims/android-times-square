// Copyright 2013 Square, Inc.

package com.squareup.timessquare;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.squareup.timessquare.MonthCellDescriptor.RangeState;

public class CalendarCellView extends TextView {

    private static final int[] STATE_SELECTABLE = {
            R.attr.state_selectable
    };
    private static final int[] STATE_CURRENT_MONTH = {
            R.attr.state_current_month
    };
    private static final int[] STATE_TODAY = {
            R.attr.state_today
    };
    private static final int[] STATE_RANGE_FIRST = {
            R.attr.state_range_first
    };
    private static final int[] STATE_RANGE_MIDDLE = {
            R.attr.state_range_middle
    };
    private static final int[] STATE_RANGE_LAST = {
            R.attr.state_range_last
    };


    private boolean isSelectable = false;
    private boolean isCurrentMonth = false;
    private boolean isToday = false;
    private RangeState rangeState = RangeState.NONE;
    private MonthCellDescriptor.ConfirmationState confirmationState = MonthCellDescriptor.ConfirmationState.UNKNOWN;


    public CalendarCellView(Context context) {
        super(context);
    }

    public CalendarCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarCellView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setSelectable(boolean isSelectable) {
        this.isSelectable = isSelectable;
        refreshDrawableState();
    }

    public void setCurrentMonth(boolean isCurrentMonth) {
        this.isCurrentMonth = isCurrentMonth;
        refreshDrawableState();
    }

    public void setToday(boolean isToday) {
        this.isToday = isToday;
        refreshDrawableState();
    }

    public void setRangeState(MonthCellDescriptor.RangeState rangeState) {
        this.rangeState = rangeState;
        refreshDrawableState();
    }

    public void setConfirmationState(MonthCellDescriptor.ConfirmationState confirmationState) {
        this.confirmationState = confirmationState;
        refreshDrawableState();

    }



    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawRec
        super.onDraw(canvas);


    }

    @Override
    public void refreshDrawableState() {
        super.refreshDrawableState();

        int numLayers = 0;


        // background color
        Drawable backgroundDrawable = null;
        if (!isCurrentMonth) {
            backgroundDrawable = getResources().getDrawable(R.drawable.inactive);
            setTextColor(getResources().getColor(R.color.calendar_text_inactive));
        } else {
            if (confirmationState == MonthCellDescriptor.ConfirmationState.CONFIRMED) {
                backgroundDrawable = getResources().getDrawable(R.drawable.confirmed);
                setTextColor(Color.WHITE);
            } else if (confirmationState == MonthCellDescriptor.ConfirmationState.UNCONFIRMED) {
                backgroundDrawable = getResources().getDrawable(R.drawable.unconfirmed);
                setTextColor(Color.WHITE);
            } else if (confirmationState == MonthCellDescriptor.ConfirmationState.UNCONFIRMED_FUTURE) {
                backgroundDrawable = getResources().getDrawable(R.drawable.active);
                setTextColor(Color.BLACK);
            } else {    // confirmationState == MonthCellDescriptor.ConfirmationState.UNKNOWN
                backgroundDrawable = getResources().getDrawable(R.drawable.active);
                setTextColor(getResources().getColor(R.color.calendar_text_inactive));
            }
        }

        numLayers++;

        // today
        Drawable todayDrawable = null;
        if (isToday) {
            numLayers++;
            todayDrawable = getResources().getDrawable(R.drawable.today);
        }

        // selected
        Drawable selectDrawable = null;
        if (isSelected() || isPressed()) {
            numLayers++;
            selectDrawable = getResources().getDrawable(R.drawable.selected);
        }

        int layerIndex = 0;
        Drawable[] layers = new Drawable[numLayers];
        layers[layerIndex++] = backgroundDrawable;


        if (todayDrawable != null) {
            layers[layerIndex++] = todayDrawable;
        }

        if (selectDrawable != null) {
            layers[layerIndex++] = selectDrawable;
        }

        LayerDrawable layerList = new LayerDrawable(layers);
        setBackground(layerList.getCurrent());
    }
}

//  @Override protected int[] onCreateDrawableState(int extraSpace) {
//    final int[] drawableState = super.onCreateDrawableState(extraSpace + 4);
//
//    if (isSelectable) {
//      mergeDrawableStates(drawableState, STATE_SELECTABLE);
//    }
//
//    if (isCurrentMonth) {
//      mergeDrawableStates(drawableState, STATE_CURRENT_MONTH);
//    }
//
//    if (isToday) {
//      mergeDrawableStates(drawableState, STATE_TODAY);
//    }
//
//    if (rangeState == MonthCellDescriptor.RangeState.FIRST) {
//      mergeDrawableStates(drawableState, STATE_RANGE_FIRST);
//    } else if (rangeState == MonthCellDescriptor.RangeState.MIDDLE) {
//      mergeDrawableStates(drawableState, STATE_RANGE_MIDDLE);
//    } else if (rangeState == RangeState.LAST) {
//      mergeDrawableStates(drawableState, STATE_RANGE_LAST);
//    }
//
//
//    return drawableState;
//  }