package heyi.com.my_aar.utils;

import android.app.Activity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by sev-14 on 2016/11/22.
 */

public class InputUtils {
    private static InputUtils utils ;
    public static InputUtils getSingleInstance(){
        if (utils == null){
            utils = new InputUtils();
        }
        return utils;
    }
    public void hideSoftInputMethod(final EditText ed, Activity context){
        context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        int currentVersion = android.os.Build.VERSION.SDK_INT;
        String methodName = null;
        if(currentVersion >= 16){
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        }
        else if(currentVersion >= 14){
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }

        if(methodName == null){
            ed.setInputType(InputType.TYPE_NULL);
        }
        else{
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName, boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(ed, false);
            } catch (NoSuchMethodException e) {
                ed.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    //获取当前焦点位置
    public static void getFocusableLocation(final Activity activity){
        Runnable run2 = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    View rootview = activity.getWindow().getDecorView();
                    View aaa = rootview.findFocus();
                    Log.i("tag", aaa.toString());
                }
            }
        };
        new Thread(run2).start();
    }
    //Unicode转为汉字
    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        unicodeStr = unicodeStr.replace("\"","");
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }
    //出去特殊字符
    public static String subSpace(String s) {
        if (s.contains("\u001D")) {
            s = s.replace("\u001D", "");
        }
//        if (s.contains("+")) {
//            s = s.replace("+", "%2B");
//        }
//        if (s.contains("$")) {
//            s = s.replace("$", "-");
//        }
        return s;
    }
    //判断是否为内网货位
    public static boolean isLocation(String location) {
        if (location.length() == 7) {
            return true;
        }
        if (location.contains("-HY-")) {
            return true;
        }
        return false;
    }

    public static boolean isGoodsList(String code) {
        if (code.startsWith("FH")) {
            return true;
        }else {
            return false;
        }
    }

}
