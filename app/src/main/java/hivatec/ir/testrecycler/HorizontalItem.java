package hivatec.ir.testrecycler;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by ashkan on 1/7/18.
 */

public class HorizontalItem extends ItemHolder<HorizontalItem.Holder> {


    @Override
    public int getLayoutResourceId() {
        return R.layout.horizontal_item_row;
    }

    @Override
    public Holder getNewHolder(View view) {
        return new Holder(view);
    }

    @Override
    public void fillHolder(Holder viewHolder) {

        ArrayList movies = new ArrayList<>();

        movies.add(new Star(0, "asdfdasfasdf"));
        movies.add(new Star(1, "eryrtyertyrety"));
        movies.add(new Star(3, "hgjfgjgfhjgfhj"));
        movies.add(new Star(2, "cxbxcvbxcbxcv"));
        movies.add(new Star(2, "cxbxcvbxcbxcv"));
        movies.add(new Star(2, "cxbxcvbxcbxcv"));
        movies.add(new Star(2, "cxbxcvbxcbxcv"));
        movies.add(new Star(2, "cxbxcvbxcbxcv"));


        RecyclerAdapter adapter = new RecyclerAdapter(movies);

        viewHolder.recyclerView.setAdapter(adapter);
        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(viewHolder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));

    }

    public class Holder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;


        public Holder(View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.recycler);
        }
    }
}
