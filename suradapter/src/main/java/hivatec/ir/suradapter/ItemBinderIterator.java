package hivatec.ir.suradapter;

import android.content.Context;
import android.view.View;

/**
 * Created by ashkan on 3/24/18.
 */

public class ItemBinderIterator<T> implements ItemBinder {


	public  T item;
	public ItemHolderIterator<T> iterator;

	public ItemBinderIterator(T item, ItemHolderIterator<T> iterator){

		this.item = item;
		this.iterator = iterator;
	}

	@Override
	public int getResourceId() {
		return iterator.resourceId;
	}

	@Override
	public void bindToHolder(final ItemHolder binder, Context context, Object listener) {
		iterator.bindToHolder(binder, item);

		binder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				iterator.itemClicked(binder, item);
			}
		});
	}
}
