package hivatec.ir.testrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ashkan on 1/6/18.
 */

public class Star extends ItemHolder<Star.StarHolder> {

    int id;
    String name;


    public Star(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.star_list_row;
    }

    @Override
    public StarHolder getNewHolder(View view) {
        return new StarHolder(view);
    }

    @Override
    public void fillHolder(StarHolder viewHolder) {

        viewHolder.idTxt.setText(id + "");
        viewHolder.nameTxt.setText(name);
    }


    public class StarHolder extends RecyclerView.ViewHolder {

        TextView nameTxt;
        TextView idTxt;

        public StarHolder(View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.nameStarTxt);
            idTxt = itemView.findViewById(R.id.idStarTxt);
        }
    }
}
