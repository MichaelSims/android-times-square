// Copyright 2012 Square, Inc.

package com.squareup.timessquare;

import java.util.Date;

/** Describes the state of a particular date cell in a {@link MonthView}. */
public class MonthCellDescriptor {
  public enum RangeState {
    NONE, FIRST, MIDDLE, LAST
  }

  public enum ConfirmationState {
    UNKNOWN, UNCONFIRMED, CONFIRMED, UNCONFIRMED_FUTURE
  }


  private final Date date;
  private final int value;
  private final boolean isCurrentMonth;
  private boolean isSelected;
  private final boolean isToday;
  private final boolean isSelectable;


  private RangeState rangeState;
  private ConfirmationState confirmationState;

  MonthCellDescriptor(Date date, boolean currentMonth, boolean selectable, boolean selected,
      boolean today, int value, RangeState rangeState, ConfirmationState confirmationState) {
    this.date = date;
    isCurrentMonth = currentMonth;
    isSelectable = selectable;
    isSelected = selected;
    isToday = today;
    this.value = value;
    this.rangeState = rangeState;
    this.confirmationState = confirmationState;
  }

  public Date getDate() {
    return date;
  }

  public boolean isCurrentMonth() {
    return isCurrentMonth;
  }

  public boolean isSelectable() {
    return isSelectable;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }

  public boolean isToday() {
    return isToday;
  }

  public RangeState getRangeState() {
    return rangeState;
  }

    public ConfirmationState getConfirmationState() {
        return confirmationState;
    }

  public void setRangeState(RangeState rangeState) {
    this.rangeState = rangeState;
  }

  public int getValue() {
    return value;
  }

  @Override public String toString() {
    return "MonthCellDescriptor{"
        + "date="
        + date
        + ", ( confirmationState="
        + confirmationState
        + " ), value="
        + value
        + "), isCurrentMonth="
        + isCurrentMonth
        + ", isSelected="
        + isSelected
        + ", isToday="
        + isToday
        + ", isSelectable="
        + isSelectable
        + ", rangeState="
        + rangeState
        + '}';
  }
}
