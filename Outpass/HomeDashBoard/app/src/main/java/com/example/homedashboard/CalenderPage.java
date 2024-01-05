package com.example.homedashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalenderPage extends AppCompatActivity {

    private final Calendar calendar = Calendar.getInstance();
    public MaterialCalendarView materialCalendarView;
    public HashSet<CalendarDay> hs = new HashSet<>();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Initialization
        materialCalendarView = findViewById(R.id.calendarView);

        switch (Dashboard.x) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                choose(Dashboard.sList);
                break;
            default:
                break;
        }

        materialCalendarView.setSelectionColor(Color.BLACK);
        materialCalendarView.setDateSelected(CalendarDay.today(), true);
        OneDayDecorator oneDayDecorator = new OneDayDecorator(this, Color.BLACK, hs);

        materialCalendarView.addDecorators(oneDayDecorator);

        //Edit

    }

    private void choose(ArrayList<String> ss) {
        for (String s : ss) {
            try {
                calendar.setTime(Objects.requireNonNull(format.parse(s)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            hs.add(CalendarDay.from(calendar));
        }
    }
}
