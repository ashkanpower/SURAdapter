SURAdapter
====================

An easy and simple way to turn your models into RecyclerView Cells.

Just take any model and implement ItemHolder interface to it and fill a binder object. thats it!

How to get
--------
You can copy and paste the files in your project from this [link](https://github.com/ashkanpower/SURAdapter/tree/master/suradapter/src/main/java/hivatec/ir/suradapter), it is just 3 files and you can edit it as you like.
and also get it by gradle :

```gradle

  //you sould add jitpack to your repositories 
  allprojects {
	repositories {
	    ...
	    maven { url 'https://jitpack.io' }
	}
  }
  
  //and then
  dependencies {
    implementation 'com.android.support:recyclerview-v7:26.1.0'
    compile 'com.github.ashkanpower:SURAdapter:v3.0.1'
  }

```

How to use
--------

Imagine you have a model and xml for a recycler cell :

```java
public class Model {

  String title;
  int rate;
  int imageRes;
 
  public Model(String title, int rate, int imageRes) {
        this.title = title;
        this.rate = rate;
        this.imageRes = imageRes;
    }
}
```
and also an XML file named 'item_model.xml'

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    >
    
    <ImageView

        android:id="@+id/image"
        android:layout_width="100dp"
        android:layout_height="100dp" />


    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">


        <TextView
            android:id="@+id/title"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />


        <TextView
            android:id="@+id/rating"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

    </LinearLayout>


</LinearLayout>
```

now just implement 'ItemBinder' interface for your model

```java
class Model implements ItemBinder{

    String title;
    int rate;
    int imageRes;

    public Model(String title, int rate, int imageRes) {
        this.title = title;
        this.rate = rate;
        this.imageRes = imageRes;
    }

    @Override
    public int getResourceId() {
        return R.layout.item_model; //set your xml file id
    }

    @Override
    public void bindToHolder(ItemHolder itemHolder, Context context, Object listener) {
        
        //now just find your view, cast it, and use it
        itemHolder.<ImageView>find(R.id.image).setImageResource(imageRes);
        itemHolder.<TextView>find(R.id.title).setText(title);
        itemHolder.<TextView>find(R.id.rating).setText("rating is : " + rate);
    }
}
```

now just make an object from SURAdapter and feed it with your model and set it to recyclerView as adapter.

```java
        ArrayList items = new ArrayList();

        items.add(new Model("model #1", 4, R.drawable.success));
        items.add(new Model("model #2", 2, R.drawable.success));
        items.add(new Model("model #3", 1, R.drawable.warning));
        items.add(new Model("model #4", 3, R.drawable.success));
        items.add(new Model("model #5", 5, R.drawable.warning));

        SURAdapter adapter = new SURAdapter(items);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
```

that's it!

you can add an item click listener to a specific Class like this :

```java
        adapter.setOnItemClickListener(Movie.class, new OnItemClickListener<Movie>() {
            @Override
            public void onItemClicked(Movie item, ItemHolder holder) {

                Toast.makeText(MainActivity.this, item.toString(), Toast.LENGTH_SHORT).show();
                holder.<TextView>find(R.id.id).setText("this was clicked");
            }
        });

````

you can also pass a listener to each type of items you like :

```java

	public interface MovieItemListener {
        
              void onLongClickListener(Movie item);
    	}
        
	SURAdapter adapter = new SURAdapter(items);
	adapter.setItemsListener(Movie.class, new MovieItemListener() {
            @Override
            public void onLongClickListener(Movie item) {
                //do something when Long clicked
            }
        });
```
and then 

```java

 public void bindToHolder(ItemHolder itemHolder, Context context, Object listener) {
 
 	itemHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
	    
	    	if(listener instanceof MovieItemListener){
                    ((MovieItemListener) listener).onDoubleClickListener(Movie.this);
                }
                return false;
            }
        });
 }
```

it sends a custom listener to all Movie item.
