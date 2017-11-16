package heyi.com.my_aar.net;

import java.io.IOException;

import heyi.com.my_aar.utils.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sev-14 on 2017/11/14.
 */

public class IRequest {

    public void startRequest(Request request, final IResponse iResponse) {
        OkHttpUtils.getOkHttpClientInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iResponse.net_error();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                iResponse.success(response);
            }
        });


    }
}
