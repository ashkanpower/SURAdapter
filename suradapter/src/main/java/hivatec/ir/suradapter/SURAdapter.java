package hivatec.ir.suradapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ashkan on 2/1/18.
 */

public class SURAdapter extends RecyclerView.Adapter<ItemHolder> {

    ArrayList<ItemBinder> items = new ArrayList<>();

    HashMap<Class, Object> itemsListenerMap = new HashMap<>();

    public SURAdapter(ArrayList items) {

        for (Object obj : items) {
            if (obj instanceof ItemBinder) {

                this.items.add((ItemBinder) obj);
            }
        }
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);

        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {

        Object listener = itemsListenerMap.get(items.get(position).getClass());
        items.get(position).bindToHolder(holder, holder.itemView.getContext(), listener);
    }


    @Override
    public int getItemViewType(int position) {
        return items.get(position).getResourceId();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void setItemsListener(Class forItemWithClass, Object itemsListener) {
        this.itemsListenerMap.put(forItemWithClass, itemsListener);
    }

    public void addItem(ItemBinder item) {

        this.items.add(item);
    }

    public void addItems(ArrayList items) {
        for (Object obj : items) {
            if (obj instanceof ItemBinder) {

                this.items.add((ItemBinder) obj);
            }
        }
    }

    public void setItems(ArrayList items) {
        this.items = new ArrayList<>();
        for (Object obj : items) {
            if (obj instanceof ItemBinder) {

                this.items.add((ItemBinder) obj);
            }
        }
    }

    public ArrayList getItems() {

        return items;
    }


    public void removeItem(ItemBinder item){

        items.remove(item);
    }
}