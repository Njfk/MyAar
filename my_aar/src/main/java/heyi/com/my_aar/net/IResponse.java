package heyi.com.my_aar.net;

import okhttp3.Response;

/**
 * Created by sev-14 on 2017/11/14.
 */

public interface IResponse {
    void net_error();
    void success(Response response);
}
