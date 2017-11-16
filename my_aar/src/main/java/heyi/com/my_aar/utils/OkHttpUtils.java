package heyi.com.my_aar.utils;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by sev-14 on 2017/4/12.
 */

public class OkHttpUtils {

    private static OkHttpClient mClient;
    private static Gson gson;

    public static OkHttpClient getOkHttpClientInstance() {
        if (mClient == null) {
            mClient = new OkHttpClient();
            OkHttpClient.Builder builder = mClient.newBuilder();
            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.readTimeout(15, TimeUnit.SECONDS);
            builder.writeTimeout(15, TimeUnit.SECONDS);
            mClient = builder.build();
        }
        return mClient;
    }

    public static Gson getGsonInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }


}
