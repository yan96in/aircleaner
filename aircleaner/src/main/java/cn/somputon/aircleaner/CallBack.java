package cn.somputon.aircleaner;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;

/**
 */
public abstract class CallBack<T> implements Callback.CacheCallback<T> {
    @Override
    public boolean onCache(T o) {
        return false;
    }

    @Override
    public void onError(Throwable throwable, boolean b) {
        LogUtil.v("Error");
    }

    @Override
    public void onCancelled(CancelledException e) {

    }

    @Override
    public void onFinished() {

    }
}
