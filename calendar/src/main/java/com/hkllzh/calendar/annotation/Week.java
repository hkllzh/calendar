package com.hkllzh.calendar.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({Week.MONDAY, Week.TUESDAY, Week.WEDNESDAY, Week.THURSDAY, Week.FRIDAY, Week.SATURDAY, Week.SUNDAY})
@Retention(RetentionPolicy.SOURCE)
public @interface Week {

    /**
     * 星期一 Mon Monday
     * 星期二 Tue Tuesday
     * 星期三 Wed Wednesday
     * 星期四 Thu Thursday
     * 星期五 Fri Friday
     * 星期六 Sat Saturday
     * 星期日 Sun Sunday
     */
    int MONDAY = 1;
    int TUESDAY = 2;
    int WEDNESDAY = 3;
    int THURSDAY = 4;
    int FRIDAY = 5;
    int SATURDAY = 6;
    int SUNDAY = 7;
}