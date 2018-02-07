package hivatec.ir.suradapter;

import android.content.Context;

/**
 * Created by ashkan on 2/1/18.
 */

public interface ItemBinder {

    int getResourceId();
    void bindToHolder(ItemHolder binder, Context context, Object listener);

}
