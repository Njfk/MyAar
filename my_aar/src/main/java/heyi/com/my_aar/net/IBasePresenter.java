package heyi.com.my_aar.net;

import android.os.Handler;
import android.os.Looper;


/**
 * Created by sev-14 on 2017/8/23.
 */

public class IBasePresenter {
    public Handler handler = new Handler(Looper.getMainLooper());
    public IRequest iRequest = new IRequest();

    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
