package hivatec.ir.testrecycler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ashkan on 1/7/18.
 */

public class Movie extends ItemHolder<Movie.Holder> {

    int id;
    String name;

    public Movie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.movie_list_row;
    }

    @Override
    public Holder getNewHolder(View view) {
        return new Holder(view);
    }

    @Override
    public void fillHolder(final Holder viewHolder) {

        viewHolder.nameView.setText(name);
        viewHolder.idView.setText(id + "");

        final RelativeLayout parent = (RelativeLayout) viewHolder.img1.getParent();

        viewHolder.img1.setVisibility(View.INVISIBLE);
        parent.post(new Runnable() {
            @Override
            public void run() {

                Log.i("cell", id + " " + parent.getWidth() + " " + parent.getHeight());

                float width = parent.getWidth();
                float height = parent.getHeight();

                float widthImg1 = viewHolder.img1.getWidth();
                float heightImg1 = viewHolder.img1.getHeight();

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(40, 40);

                lp.setMargins(
                       Math.round(width / 2 - widthImg1 / 2), 40, 0, 0
                );

                viewHolder.img1.setLayoutParams(lp);

                viewHolder.img1.setVisibility(View.VISIBLE);
            }
        });
    }


    public class Holder extends RecyclerView.ViewHolder {


        TextView nameView;
        TextView idView;

        ImageView img1;
        ImageView img2;


        public Holder(View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.nameTxt);
            idView = itemView.findViewById(R.id.idTxt);

            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
        }
    }
}
