package heyi.com.my_aar.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import heyi.com.my_aar.R;


/**
 * Created by sev-14 on 2017/11/24.
 */

public class MyLoadinDialog {
    private Dialog dialog = null;
    Context context;
    public MyLoadinDialog(Context context) {
      this.context = context;
    }

    public void showDialog(String msg) {
        if (dialog != null) {
            dialog.show();
        }else {
            dialog = new Dialog(context, R.style.loading_dialog);
//        dialog = new Dialog(context);
            //加载布局
            View layout = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null);
            TextView textView = (TextView) layout.findViewById(R.id.text);
            textView.setText(msg);
            //加载自定义布局
            dialog.setContentView(layout);
            //设置不可取消
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog.cancel();
        }
    }

}
