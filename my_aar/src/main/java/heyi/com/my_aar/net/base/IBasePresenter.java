package heyi.com.pda.net.base;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by sev-14 on 2018/1/24.
 */

public abstract class IBasePresenter {
    public Handler handler = new Handler(Looper.getMainLooper());
    public IRequest iRequest = new IRequest();
    public boolean displayable = true;//是否显示加载框
    public void post(Runnable runnable) {
        handler.post(runnable);
    }

    public boolean isDisplayable() {
        return displayable;
    }

    public void setDisplayable(boolean displayable) {
        this.displayable = displayable;
    }

    public abstract void showDialog();
    public abstract void dismissDialog();
}
