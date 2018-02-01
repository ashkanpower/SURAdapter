package hivatec.ir.testrecycler.HivaRecycler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ashkan on 2/1/18.
 */

public class HivaRecyclerAdapter extends RecyclerView.Adapter<ItemHolder> {

    ArrayList<ItemBinder> items = new ArrayList<>();


    public HivaRecyclerAdapter(ArrayList<ItemBinder> items){
        for (Object obj : items) {

            if(obj instanceof ItemBinder) {

                this.items.add((ItemBinder) obj);
            }
        }
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);

        Log.i("hivatest", "new holder for : " + viewType);

        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {

        items.get(position).bindToHolder(holder, holder.itemView.getContext());
    }


    @Override
    public int getItemViewType(int position) {
        return items.get(position).getResourceId();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
