package hivatec.ir.testrecycler.HivaRecycler;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hivatec.ir.testrecycler.R;

/**
 * Created by ashkan on 2/1/18.
 */

public class Soccer implements ItemBinder {

    String player;
    String logo;


    public Soccer(String player, String logo) {
        this.player = player;
        this.logo = logo;
    }

    @Override
    public int getResourceId() {
        return R.layout.item_soccer;
    }

    @Override
    public void bindToHolder(ItemHolder binder, Context context) {

        binder.<TextView>find(R.id.textView).setText(player);

        Glide.with(context)
                .load(logo)
                .into(binder.<ImageView>find(R.id.logo));
    }
}
