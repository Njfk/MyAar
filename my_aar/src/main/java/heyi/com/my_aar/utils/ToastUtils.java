package heyi.com.my_aar.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import heyi.com.my_aar.R;
import heyi.com.my_aar.view.CustomDialog;


/**
 * Created by sev-14 on 2017/3/27.
 */

public class ToastUtils {
    static Toast toast = null;

    public static void getToast(String s, Context context) {
        if (toast == null) {
            toast = new Toast(context);
            toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        } else {
            toast.setText(s);
        }
        toast.show();
    }

    public static void net_error(Context context) {
        if (toast == null) {
            toast = new Toast(context);
            toast = Toast.makeText(context, context.getString(R.string.net_error), Toast.LENGTH_SHORT);
        } else {
            toast.setText(context.getString(R.string.net_error));

        }
        toast.show();
    }

    public static void gson_error(Context context) {
        if (toast == null) {
            toast = new Toast(context);
            toast = Toast.makeText(context, context.getString(R.string.gson_error), Toast.LENGTH_SHORT);
        } else {
            toast.setText(context.getString(R.string.gson_error));
        }
        toast.show();
    }


    public static void showToast(final Context var0, final String var1) {
        (new Handler(Looper.getMainLooper())).post(new Runnable() {
            public void run() {
                Toast.makeText(var0, var1, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showNoWaitToast(final Context var0, final String var1) {
        (new Handler(Looper.getMainLooper())).post(new Runnable() {
            public void run() {
                if (ToastUtils.toast == null) {
                    ToastUtils.toast = Toast.makeText(var0, var1, Toast.LENGTH_SHORT);
                    ToastUtils.toast.show();
                } else {
                    ToastUtils.toast.setText(var1);
                    ToastUtils.toast.show();
                }

            }
        });
    }

    public static void showMyToast(String s, final Activity context) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_toast, null);
        TextView toast_tv = (TextView) inflate.findViewById(R.id.toast_text);
        toast_tv.setText(s);
        toast.setView(inflate);
        toast.show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                context.finish();
            }
        });
        thread.start();

    }
    public static void showMyToastNoFinish(String s, final Activity context) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_toast, null);
        TextView toast_tv = (TextView) inflate.findViewById(R.id.toast_text);
        toast_tv.setText(s);
        toast.setView(inflate);
        toast.show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }
    public static void showCustomDialog(String warmInfo, final Activity context) {
        CustomDialog.Builder customBuilder = new
                CustomDialog.Builder(context);
        customBuilder.setMessage(warmInfo);
        final CustomDialog customDialog = customBuilder.create();
        customDialog.setCancelable(false);
        customDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                customDialog.dismiss();
                context.finish();
            }
        }, 1000);
    }

}
