package hivatec.ir.suradapter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by ashkan on 2/10/18.
 */

public class SURAdapterExtended extends SURAdapter implements ErrorRetryItem.RetryListener {

    private ItemBinder emptyItem;
    private ItemBinder loadingItem;
    private ErrorRetryItem errorRetryItem;
    private ErrorRetryItem.RetryListener errorRetryListener;

    //private boolean loading = false;
    private boolean isLoadingMore = false;

    private boolean hasError = false;

    PagingRecyclerListener loadMoreRefreshListener;


    public SURAdapterExtended(final ArrayList items) {
        super(items);
    }

    public void setEmptyItem(ItemBinder item){

        this.emptyItem = item;
    }

    public void setLoadingItem(ItemBinder item){

        this.loadingItem = item;
    }

    public void setErrorRetryItem(ErrorRetryItem item, ErrorRetryItem.RetryListener listener){

        this.errorRetryItem = item;
        this.errorRetryListener = listener;
    }

    @Override
    public int getItemViewType(int position) {

        if(errorRetryItem != null && hasError && position == items.size()){

            return errorRetryItem.getResourceId();
        }

        if(loadingItem != null && isLoadingMore && position == items.size()){

            return loadingItem.getResourceId();
        }

        if(emptyItem != null && items.size() == 0){

            return emptyItem.getResourceId();
        }

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {

        if(errorRetryItem != null && hasError){

            return items.size() + 1;
        }

        if(loadingItem != null && isLoadingMore){

            return items.size() + 1;
        }

        if(emptyItem != null && items.size() == 0){

            return 1;
        }

        return items.size();
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {


            if (position < items.size()) {

                super.onBindViewHolder(holder, position);

            }else{


                if(errorRetryItem != null && hasError){

                    errorRetryItem.bindToHolder(holder, holder.itemView.getContext(), this);
                }

                if(loadingItem != null && isLoadingMore){

                    loadingItem.bindToHolder(holder, holder.itemView.getContext(), null);
                }

                if(emptyItem != null && items.size() == 0){

                    emptyItem.bindToHolder(holder, holder.itemView.getContext(), null);
                }
            }

    }


    public void setLoadMoreItems(boolean flag){

        isLoadingMore = flag;

        notifyDataSetChanged();
    }

    public void setHasError(boolean flag){

        hasError = flag;

        notifyDataSetChanged();
    }


    public void makeEndless(final RecyclerView recycler){


        recycler.addOnScrollListener(new OnScrollListener() {

            int state = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //Log.i("recycler", newState + "");

                state = newState;

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                if(hasError){
                    return;
                }


                View child = recycler.getChildAt(recycler.getChildCount() - 1);
                int diff = (child.getBottom() - (recycler.getHeight() + recycler.getScrollY()));

                if (diff == 0) {

                    loadMoreRefreshListener.loadMoreData(false);
                }
            }
        });
    }

    @Override
    public void retryLoading() {

        isLoadingMore = true;
        hasError = false;

        this.notifyDataSetChanged();
        errorRetryListener.retryLoading();
    }

    public interface PagingRecyclerListener{

        void loadMoreData(boolean shouldReset);
    }


}

/*

public class SURAdapterExtended extends SURAdapter implements PagingRecyclerLoadMoreListener, ErrorRetryItem.RetryListener {

    private ItemBinder emptyItem;
    private ItemBinder loadingItem;
    private ErrorRetryItem errorRetryItem;
    private SwipeRefreshLayout refreshLayout;

    //private boolean loading = false;
    private boolean shouldLoadMore = false;
    private boolean isLoadingMore = false;
    private boolean isRefreshing = false;

    private int page = 0;

    private boolean hasError = false;

    PagingRecyclerListener paginRefreshListener;
    private RecyclerView recycler;
    PagingRecyclerListener loadMoreRefreshListener;

    public SURAdapterExtended(final ArrayList items) {
        super(items);
    }

    public void setEmptyItem(ItemBinder item){

        this.emptyItem = item;
    }

    public void setLoadingItem(ItemBinder item){

        this.loadingItem = item;
    }


    public void setErrorRetryItem(ErrorRetryItem item){

        this.errorRetryItem = item;
    }



    public void setPullToRefresh(SwipeRefreshLayout swipeLayout, final PagingRecyclerListener listener){

        refreshLayout = swipeLayout;
        paginRefreshListener = listener;

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(paginRefreshListener != null) {
                    page = 0;
                    hasError = false;
                    isRefreshing = true;
                    items.clear();
                    paginRefreshListener.loadMoreData(true, page);
                }
            }
        });
    }


    @Override
    public int getItemViewType(int position) {

        if(errorRetryItem != null && hasError && position == items.size()){

            return errorRetryItem.getResourceId();
        }

        if(loadingItem != null && isLoadingMore && position == items.size()){

            return loadingItem.getResourceId();
        }

        if(emptyItem != null && items.size() == 0){

            return emptyItem.getResourceId();
        }

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {

        if(errorRetryItem != null && hasError){

            return items.size() + 1;
        }

        if(loadingItem != null && isLoadingMore){

            return items.size() + 1;
        }

        if(emptyItem != null && items.size() == 0){

            return 1;
        }

        return items.size();
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {


            if (position < items.size()) {

                super.onBindViewHolder(holder, position);

            }else{


                if(errorRetryItem != null && hasError){

                    errorRetryItem.bindToHolder(holder, holder.itemView.getContext(), this);
                }

                if(loadingItem != null && isLoadingMore){

                    loadingItem.bindToHolder(holder, holder.itemView.getContext(), null);
                }

                if(emptyItem != null && items.size() == 0){

                    emptyItem.bindToHolder(holder, holder.itemView.getContext(), null);
                }
            }

    }

    public void shouldLoadMore(final RecyclerView recycler, final int itemsFromBottom, PagingRecyclerListener listener){
        this.recycler = recycler;

        loadMoreRefreshListener = listener;
        shouldLoadMore = true;

        recycler.addOnScrollListener(new OnScrollListener() {

            int state = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //Log.i("recycler", newState + "");

                state = newState;

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(!shouldLoadMore){
                    return;
                }

                if(isLoadingMore){
                    return;
                }

                if(hasError){
                    return;
                }

                */
/*
                if(state != 2){
                    return;
                }*//*


                */
/*
                RecyclerView.LayoutManager mLayoutManager = recycler.getLayoutManager();

                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int pastVisibleItems = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();


                if (recycler.getScaleY() < recycler.getHeight() || pastVisibleItems + visibleItemCount >= totalItemCount) {
                    //End of list
                    loadMoreRefreshListener.loadMoreData(false, page);
                }
                *//*



                View child = recycler.getChildAt(recycler.getChildCount() - 1);
                int diff = (child.getBottom() - (recycler.getHeight() + recycler.getScrollY()));

                Log.i("recycler",  "scrolled " + diff);


                if (diff == 0) {

                    loadMoreRefreshListener.loadMoreData(false, page);
                }
            }
        });
    }

    public void setShouldLoadMore(boolean flag){

        shouldLoadMore = flag;
    }

    @Override
    public void onStartLoadingMore() {

        isLoadingMore = true;
        hasError = false;

        recycler.post(new Runnable() {
            public void run() {

                if(!isRefreshing) {
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onFinishedLoadingMore() {

        page++;

        isLoadingMore = false;
        refreshLayout.setRefreshing(false);
        isRefreshing = false;

        this.notifyDataSetChanged();
    }

    @Override
    public void onErrorLoadingMore() {

        isLoadingMore = false;
        hasError = true;
        isRefreshing = false;
        refreshLayout.setRefreshing(false);

        this.notifyDataSetChanged();

    }

    public void setPage(int page){
        this.page = page;
    }

    public int getPage(){
        return page;
    }

    @Override
    public void retryLoading() {


        isLoadingMore = true;
        hasError = false;

        refreshLayout.setRefreshing(false);

        this.notifyDataSetChanged();

        loadMoreRefreshListener.loadMoreData(false, page);
    }

    public interface PagingRecyclerListener{

        void loadMoreData(boolean shouldReset, int page);
    }


}
*/
