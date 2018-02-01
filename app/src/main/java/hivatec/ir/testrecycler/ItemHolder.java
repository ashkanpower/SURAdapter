package hivatec.ir.testrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashkan on 1/6/18.
 */

public abstract class ItemHolder<T extends RecyclerView.ViewHolder> {

    public abstract int getLayoutResourceId();
    public View getLayout(ViewGroup view){
        View itemView = LayoutInflater.from(view.getContext())
                .inflate(getLayoutResourceId(), view, false);

        return itemView;
    }
    public abstract T getNewHolder(View view);
    public abstract void fillHolder(T viewHolder);

}
