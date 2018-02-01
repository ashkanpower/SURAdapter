package hivatec.ir.testrecycler.PersianCalendar;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import hivatec.ir.testrecycler.ItemHolder;
import hivatec.ir.testrecycler.RecyclerAdapter;

/**
 * Created by ashkan on 1/30/18.
 */

public class CalendarAdapter extends RecyclerView.Adapter  {

    ArrayList<PersianCalendar.CalendarCell> items = new ArrayList<>();

    int selectionStart = 0;
    int selectionEnd = 0;

    int monthEnd = 0;

    public CalendarAdapter(ArrayList<PersianCalendar.CalendarCell> items) {


        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return items.get(0).getNewHolder(items.get(0).getLayout(parent));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ItemHolder item = items.get(position);

        item.fillHolder(holder);
    }


    public void addItems(ArrayList items) {

        this.items.addAll(items);
    }

    public void setItems(ArrayList items) {

        this.items = new ArrayList<>();

        this.items.addAll(items);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void setSelectedItem(PersianCalendar.CalendarCell cell) {

        if(selectionEnd != 0){
            selectionStart = 0;
            selectionEnd = 0;
        }

        if(selectionStart == 0){
            selectionStart = cell.date.getDay();
        }else if(selectionStart != cell.date.getDay()){
            selectionEnd = cell.date.getDay();

            if(selectionStart > selectionEnd){

                int temp = selectionStart;
                selectionStart = selectionEnd;
                selectionEnd = temp;
            }
        }
    }

}
