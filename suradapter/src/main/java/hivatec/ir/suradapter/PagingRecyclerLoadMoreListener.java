package hivatec.ir.suradapter;

/**
 * Created by ashkan on 2/12/18.
 */

public interface PagingRecyclerLoadMoreListener{

    void onStartLoadingMore();
    void onFinishedLoadingMore();
    void onErrorLoadingMore();
}