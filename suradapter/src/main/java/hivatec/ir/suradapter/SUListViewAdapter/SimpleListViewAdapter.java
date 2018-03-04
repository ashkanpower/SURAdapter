package hivatec.ir.suradapter.SUListViewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import hivatec.ir.suradapter.ItemBinder;

/**
 * Created by ashkan on 3/4/18.
 */

public class SimpleListViewAdapter extends ArrayAdapter<ListItemBinder> {


	ArrayList<ListItemBinder> items = new ArrayList<>();
	HashMap<Class, Object> itemsListenerMap = new HashMap<>();


	public SimpleListViewAdapter(Context context, ArrayList<ListItemBinder> items){
		super(context, 0);

		this.setItems(items);

		this.notifyDataSetChanged();
	}


	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

		ListItemBinder item = items.get(position);
		int positionResId = item.getResourceId();
		ListItemHolder holder = null;
		View view = convertView;
		Object listener = itemsListenerMap.get(items.get(position).getClass());

		if(view != null && view.getTag() instanceof ListItemHolder){
			holder = (ListItemHolder) convertView.getTag();
		}

		if(holder != null && holder.getResourceId() == positionResId){

			item.bindToHolder(holder, getContext(),listener);

		}else{

			Log.i("test", "new view for id " + positionResId);

			view = LayoutInflater.from(getContext())
					.inflate(positionResId, parent, false);

			holder = new ListItemHolder(view, positionResId);
			view.setTag(holder);

			item.bindToHolder(holder, getContext(),listener);
		}

		return view;
	}

	public void setItemsListener(Class forItemWithClass, Object itemsListener) {
		this.itemsListenerMap.put(forItemWithClass, itemsListener);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	public void setItems(ArrayList<ListItemBinder> items){

		for (Object obj : items) {

			if(obj instanceof ListItemBinder) {

				this.items.add((ListItemBinder) obj);
			}
		}
	}
}
