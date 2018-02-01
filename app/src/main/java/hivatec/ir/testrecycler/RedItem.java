package hivatec.ir.testrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ashkan on 1/6/18.
 */

public class RedItem extends ItemHolder<RedItem.RedItemHolder> {


    @Override
    public int getLayoutResourceId() {
        return R.layout.red_item_list_row;
    }

    @Override
    public RedItemHolder getNewHolder(View view) {
        return new RedItemHolder(view);
    }

    @Override
    public void fillHolder(RedItemHolder viewHolder) {

    }

    public class RedItemHolder extends RecyclerView.ViewHolder {

        public RedItemHolder(View itemView) {
            super(itemView);
        }
    }
}
