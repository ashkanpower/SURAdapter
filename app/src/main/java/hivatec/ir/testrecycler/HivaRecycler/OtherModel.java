package hivatec.ir.testrecycler.HivaRecycler;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import hivatec.ir.testrecycler.R;

/**
 * Created by ashkan on 2/1/18.
 */

public class OtherModel implements ItemBinder  {


    public OtherModel(String image) {
        this.image = image;
    }

    String image;




    @Override
    public int getResourceId() {
        return R.layout.layout_other_model;
    }

    @Override
    public void bindToHolder(ItemHolder binder, Context context) {

        Glide.with(context)
                .load(R.drawable.a100)
                .into(binder.<ImageView>find(R.id.image));

    }
}
