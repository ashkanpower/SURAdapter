package hivatec.ir.testrecycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import hivatec.ir.testrecycler.HivaRecycler.HivaRecyclerAdapter;
import hivatec.ir.testrecycler.HivaRecycler.Model;
import hivatec.ir.testrecycler.HivaRecycler.OtherModel;
import hivatec.ir.testrecycler.HivaRecycler.Soccer;

/**
 * Created by ashkan on 1/6/18.
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        ArrayList models = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            models.add(new Model(i));

            if(Math.random() < 0.2){

                models.add(new Soccer("messi", "https://picsum.photos/100/100"));
            }
        }


        HivaRecyclerAdapter adapter = new HivaRecyclerAdapter(models);
        FamiliarRecyclerView rv = findViewById(R.id.recycler);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



        /*
        ArrayList movies = new ArrayList<>();

        movies.add(new Movie(0, "asdfdasfasdf"));
        movies.add(new Movie(1, "eryrtyertyrety"));
        movies.add(new Movie(3, "hgjfgjgfhjgfhj"));
        movies.add(new Movie(2, "cxbxcvbxcbxcv"));
        movies.add(new HorizontalItem());



        movies.add(new Star(2, "cxbxcvbxcbxcv"));
        movies.add(new Star(2, "cxbxcvbxcbxcv"));

        for (int i = 0; i < 100; i++){

            if(i / 10 == 0){

                movies.add(new RedItem());
            }

            movies.add(new Movie(i, "cxbxcvbxcbxcv"));
        }

        movies.add(new RedItem());


        RecyclerAdapter adapter = new RecyclerAdapter(movies);

        FamiliarRecyclerView rv = findViewById(R.id.recycler);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



        rv.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {

                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }


        });

*/

    }
}
