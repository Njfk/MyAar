package heyi.com.my_aar.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sev-14 on 2017/9/7.
 */

public class SharePerenceUtils {
    //用户名
    public static final String user_title = "User";
    public static final String user_id = "id";
    public static final String user_name = "name";
    public static final String user_department_id = "department_id";


    public static SharedPreferences getInstance(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static String getStringValue(SharedPreferences sharedPreferences, String name) {
        String string = sharedPreferences.getString(name, "");
        return string;
    }

    public static void clear(Context context, String name) {
        SharedPreferences instance = getInstance(context, name);
        SharedPreferences.Editor clear = instance.edit().clear();
        clear.commit();

    }
}
