package hivatec.ir.suradapter;

import android.content.Context;

/**
 * Created by ashkan on 2/12/18.
 */

public abstract class ErrorRetryItem implements ItemBinder {


    @Override
    public void bindToHolder(ItemHolder binder, Context context, Object listener) {

        bindToHolder(binder, context, listener);
    }

    public abstract void bindToHolder(ItemHolder binder, Context context, RetryListener listener);

    public interface RetryListener{

        void retryLoading();
    }
}
