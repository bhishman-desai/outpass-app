package com.example.homedashboard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.style.RelativeSizeSpan;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

class OneDayDecorator implements DayViewDecorator {

    private final HashSet<CalendarDay> dates;
    Drawable drawable;


    OneDayDecorator(Context context, int color, Collection<CalendarDay> dates) {
        this.dates = new HashSet<>(dates);

        drawable = context.getResources().getDrawable(R.drawable.date_bg);
    }


    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {

        //view.addSpan(new DotSpan(10, Color.BLUE));
        view.addSpan(new RelativeSizeSpan(1.7f));
        view.setBackgroundDrawable(drawable);
    }
}
