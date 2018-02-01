package hivatec.ir.testrecycler;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import hivatec.ir.testrecycler.PersianCalendar.JalaliCalendar;
import hivatec.ir.testrecycler.PersianCalendar.PersianCalendar;

public class CalendarActivity extends AppCompatActivity {

    RecyclerView calendarRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*

        LinearLayout calendarLayout = findViewById(R.id.calendarLayout);

        for (int i = 0; i < calendarLayout.getChildCount(); i++){

            PersianCalendar calendar = (PersianCalendar) calendarLayout.getChildAt(i);

            calendar.setDate( new JalaliCalendar(1396, i + 8, 1));
        }

*/


        calendarRecycler = findViewById(R.id.calendarRecycler);

        TheCalendarCell cell = new TheCalendarCell(1396, 10);
        TheCalendarCell cell1 = new TheCalendarCell(1396, 10);
        TheCalendarCell cell2 = new TheCalendarCell(1396, 10);
        TheCalendarCell cell3 = new TheCalendarCell(1396, 10);
        TheCalendarCell cell4 = new TheCalendarCell(1396, 10);
        TheCalendarCell cell5 = new TheCalendarCell(1396, 10);

        ArrayList<TheCalendarCell> cells = new ArrayList<>();
        cells.add(cell);
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        cells.add(cell4);
        cells.add(cell5);

        calendarRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        calendarRecycler.setAdapter(new RecyclerAdapter(cells));




    }

}
