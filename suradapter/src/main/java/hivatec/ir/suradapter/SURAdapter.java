package hivatec.ir.suradapter;

import android.content.Context;
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
    HashMap<Class, OnItemClickListener> itemClickListenerMap = new HashMap<>();

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
    public void onBindViewHolder(final ItemHolder holder, int position) {

        Object listener = itemsListenerMap.get(items.get(position).getClass());
        holder.context = holder.itemView.getContext();
        items.get(position).bindToHolder(holder, holder.itemView.getContext(), listener);

        final OnItemClickListener clickListener = itemClickListenerMap.get(items.get(position).getClass());
        if(clickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        clickListener.onItemClicked(items.get(holder.getAdapterPosition()), holder);
                    }catch (Exception e){

                    }
                }
            });
        }




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

    public <T> void setOnItemClickListener(Class<T> forItemWithClass, OnItemClickListener<T> listener) {
        this.itemClickListenerMap.put(forItemWithClass, listener);
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

    public void clearItems(){

        items = new ArrayList<>();
    }


    public <T> void forEach(final ArrayList<T> items, final ItemHolderIterator<T> iterator){


        for (final T obj : items) {

            addItem(new ItemBinderIterator(obj, iterator));
        }
    }
}