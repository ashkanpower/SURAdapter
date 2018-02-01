package hivatec.ir.testrecycler;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import hivatec.ir.testrecycler.PersianCalendar.JalaliCalendar;
import hivatec.ir.testrecycler.PersianCalendar.PersianCalendar;

/**
 * Created by ashkan on 1/30/18.
 */

public class TheCalendarCell extends ItemHolder<TheCalendarCell.CalendarCellHolder> {

    int year = 0;
    int month = 0;

    public TheCalendarCell(int year, int month) {
        this.year = year;
        this.month = month;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.calendar_cell;
    }

    @Override
    public CalendarCellHolder getNewHolder(View view) {
        return new CalendarCellHolder(view);
    }

    @Override
    public void fillHolder(CalendarCellHolder viewHolder) {

        /*
        JalaliCalendar jc = new JalaliCalendar();
        jc.setYear(year);
        jc.setMonth(month);
        viewHolder.monthText.setText(jc.getMonthString());
        viewHolder.calendar.setDate(jc);
        */
    }

    public class CalendarCellHolder extends RecyclerView.ViewHolder {


        TextView monthText;
        PersianCalendar calendar;

        public CalendarCellHolder(View itemView) {
            super(itemView);

            monthText = itemView.findViewById(R.id.text);
            calendar = itemView.findViewById(R.id.calendar);
        }
    }
}


