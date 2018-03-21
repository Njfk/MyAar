package heyi.com.my_aar.net.base;

import java.io.IOException;

import heyi.com.pda.utils.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sev-14 on 2018/1/24.
 */

public class IRequest {
    public void startRequest(Request request, final IResponse iResult) {
        OkHttpUtils.getOkHttpClientInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iResult.net_error();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                iResult.success(response);
            }
        });

    }
}
