package hivatec.ir.suradapter.SUListViewAdapter;

import android.content.Context;

import hivatec.ir.suradapter.ItemHolder;

/**
 * Created by ashkan on 3/4/18.
 */

public interface ListItemBinder {

	int getResourceId();
	void bindToHolder(ListItemHolder binder, Context context, Object listener);
}
