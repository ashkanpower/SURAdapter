package hivatec.ir.testrecycler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import hivatec.ir.testrecycler.PersianCalendar.PersianCalendar;

/**
 * Created by ashkan on 1/6/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    ArrayList<ItemHolder> items = new ArrayList<>();
    HashMap<Type, Integer> typeMap = new HashMap<>();
    HashMap<Integer, Type> typeMapInverse = new HashMap<>();

    public RecyclerAdapter(ArrayList items) {

        for (Object obj : items) {

            if(obj instanceof ItemHolder) {

                this.items.add((ItemHolder) obj);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        Type posType = typeMapInverse.get(viewType);

        for (ItemHolder item : items){

            if(item.getClass() == posType){

                return item.getNewHolder(item.getLayout(parent));
            }
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ItemHolder item = items.get(position);

        item.fillHolder(holder);
    }

    @Override
    public int getItemViewType(int position) {

        return items.get(position).getLayoutResourceId();

        /*
        ItemHolder item = items.get(position);

        Type t = item.getClass();

        if(!typeMap.containsKey(t)){

            typeMapInverse.put(typeMap.size(), t);
            typeMap.put(t, typeMap.size());
        }

        int viewType = typeMap.get(t);

        return viewType;
        */
    }

    public void addItems(ArrayList items) {

        for (Object obj : items) {

            if(obj instanceof ItemHolder) {

                this.items.add((ItemHolder) obj);
            }
        }
    }

    public void setItems(ArrayList items) {

        this.items = new ArrayList<>();

        for (Object obj : items) {

            if(obj instanceof ItemHolder) {

                this.items.add((ItemHolder) obj);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
