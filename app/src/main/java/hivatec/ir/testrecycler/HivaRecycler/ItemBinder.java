package hivatec.ir.testrecycler.HivaRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ashkan on 2/1/18.
 */

public interface ItemBinder {

    int getResourceId();
    void bindToHolder(ItemHolder binder, Context context);

}
