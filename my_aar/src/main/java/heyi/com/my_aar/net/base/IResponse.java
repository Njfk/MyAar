package heyi.com.my_aar.net.base;

import okhttp3.Response;

/**
 * Created by sev-14 on 2018/1/24.
 */

public interface IResponse {
    void net_error();
    void success(Response response);
}
