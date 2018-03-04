package hivatec.ir.suradapter.SUListViewAdapter;

import android.view.View;

import java.util.HashMap;

/**
 * Created by ashkan on 3/4/18.
 */

public class ListItemHolder {

	View itemView;
	int resourceId;

	public ListItemHolder(View itemView, int ResourceId) {

		this.itemView = itemView;
		resourceId = ResourceId;
	}

	HashMap<Integer, View> viewMap = new HashMap<>();

	public <T extends View> T find(int viewId){

		T view;

		if(viewMap.containsKey(viewId)){

			view = (T) viewMap.get(viewId);
		}else{


			view = itemView.findViewById(viewId);
			viewMap.put(viewId, view);
		}

		return view;
	}

	public int getResourceId() {
		return resourceId;
	}

}
