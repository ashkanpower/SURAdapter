package hivatec.ir.testrecycler.PersianCalendar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TimingLogger;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import hivatec.ir.testrecycler.ItemHolder;
import hivatec.ir.testrecycler.R;
import hivatec.ir.testrecycler.RecyclerAdapter;

/**
 * Created by ashkan on 1/30/18.
 */

public class PersianCalendar extends LinearLayout {


    CalendarAdapter adapter;
    RecyclerView calendar;

    public PersianCalendar(Context context) {
        super(context);

        init();
    }

    public PersianCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }


    private void init(){



        calendar = new RecyclerView(getContext());
        calendar.setScaleX(-1);
        calendar.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        calendar.setLayoutManager(new GridLayoutManager(getContext(), 7));


        long time = System.nanoTime();

        JalaliCalendar jcToday = new JalaliCalendar();
        JalaliCalendar jc = new JalaliCalendar();
        ArrayList<CalendarCell> cells = new ArrayList<>();
        adapter = new CalendarAdapter(cells);
        adapter.monthEnd = jc.getMonthLength();

        jc.setDay(1);

        for(int i = 0; i < jc.getFirstDayOfWeek(); i++){

            CalendarCell cell = new CalendarCell(null, false, adapter);
            cells.add(cell);
        }

        for(int i = 0; i < jc.getMonthLength(); i++) {


            JalaliCalendar date = new JalaliCalendar(jc.getYear(), jc.getMonth(), i + 1);
            CalendarCell cell = new CalendarCell(date, !jcToday.isGreater(date), adapter);
            cells.add(cell);
        }


        Log.i("calendar", (System.nanoTime() - time)  + "");

        calendar.setAdapter(adapter);
        this.addView(calendar);

    }


    public void setDate(JalaliCalendar theJC){

        JalaliCalendar jcToday = new JalaliCalendar();

        JalaliCalendar jc = new JalaliCalendar();
        ArrayList<CalendarCell> cells = new ArrayList<>();

        jc.setYear(theJC.getYear());
        jc.setMonth(theJC.getMonth());
        jc.setDay(1);

        for(int i = 0; i < jc.getDayOfWeek(); i++){

            CalendarCell cell = new CalendarCell(null, false, adapter);
            cells.add(cell);
        }

        for(int i = 0; i < jc.getMonthLength(); i++) {


            JalaliCalendar date = new JalaliCalendar(jc.getYear(), jc.getMonth(), i + 1);
            CalendarCell cell = new CalendarCell(date, !jcToday.isGreater(date), adapter);
            cells.add(cell);
        }

        adapter.setItems(cells);
        calendar.setAdapter(adapter);
    }










    /////////////////
    //cell
    ////////////////
    public class CalendarCell extends ItemHolder<CalendarCellHolder> {


        JalaliCalendar date;
        CalendarAdapter adapter;
        boolean isEnabled;


        public CalendarCell(JalaliCalendar date, boolean enable, CalendarAdapter adapter) {
            this.date = date;
            this.adapter = adapter;
            this.isEnabled = enable;
        }


        @Override
        public int getLayoutResourceId() {
            return R.layout.calendar_item;
        }

        @Override
        public CalendarCellHolder getNewHolder(View view) {

            return new CalendarCellHolder(view);
        }

        @Override
        public void fillHolder(final CalendarCellHolder viewHolder) {

            if(date != null) {
                viewHolder.textView.setText(date.getDay() + "");
                viewHolder.itemView.setTag(this);

            }else{

                viewHolder.textView.setText("");
                viewHolder.itemView.setTag(null);
            }



            if(date != null) {

                if (adapter.selectionStart == this.date.getDay() && adapter.selectionEnd == 0) {
                    viewHolder.layout.setBackgroundResource(R.drawable.calendar_single);
                } else if (adapter.selectionStart == this.date.getDay() && adapter.selectionEnd != 0) {
                    viewHolder.layout.setBackgroundResource(R.drawable.calendar_start);
                } else if (adapter.selectionEnd == this.date.getDay()) {
                    viewHolder.layout.setBackgroundResource(R.drawable.calendar_end);
                } else if (adapter.selectionStart < this.date.getDay() && adapter.selectionEnd > this.date.getDay()) {
                    viewHolder.layout.setBackgroundResource(R.drawable.calendar_middle);
                }else{
                    viewHolder.layout.setBackgroundResource(0);
                }
            }else{
                viewHolder.layout.setBackgroundResource(0);
            }

            if(isEnabled){

                viewHolder.textView.setTextColor(Color.BLACK);
            }else{
                viewHolder.textView.setTextColor(Color.GRAY);
            }


            final CalendarCell self = this;

            viewHolder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(self.date != null && self.isEnabled) {

                        adapter.setSelectedItem(self);
                        adapter.notifyDataSetChanged();
                    }
                }
            });


        }
    }

    public class CalendarCellHolder extends RecyclerView.ViewHolder {

        TextView textView;
        RelativeLayout layout;

        public CalendarCellHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            layout = itemView.findViewById(R.id.layout);

            itemView.setScaleX(-1);
        }
    }



}
