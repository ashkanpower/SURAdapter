package hivatec.ir.testrecycler.HivaRecycler;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import hivatec.ir.testrecycler.R;

/**
 * Created by ashkan on 2/1/18.
 */

public class Model implements ItemBinder {

    int id = 0;

    public Model(int id){

        this.id = id;
    }

    @Override
    public int getResourceId() {
        return R.layout.item_model;
    }

    @Override
    public void bindToHolder(ItemHolder binder, Context context) {

        binder.<TextView>find(R.id.textView)
                .setText("model #" + this.id);

        TextView textView = binder.find(R.id.secondTextView);
        textView.setText("and this is second text view #" + id);
    }
}
