package heyi.com.my_aar.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import heyi.com.my_aar.R;


/**
 * Created by sev-14 on 2017/11/24.
 */

public class LoadingDialog {
    private static Dialog dialog = null;
    Context context;
    TextView textView;

    public LoadingDialog(Context context) {
        this.context = context;
    }

    public void showDialog(String msg) {

        dialog = new Dialog(context, R.style.loading_dialog);
//        dialog = new Dialog(context);
        //加载布局
        View layout = LayoutInflater.from(context).inflate(R.layout.layout_idialog, null);
        textView = (TextView) layout.findViewById(R.id.text);
        textView.setText(msg);
        //加载自定义布局
        dialog.setContentView(layout);
        //设置不可取消
        dialog.setCancelable(false);
        dialog.show();

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_UP && i == keyEvent.KEYCODE_BACK) {
                    dismissDialog();
                }
                return false;
            }
        });

    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog.cancel();
        }
    }

    public boolean isShowing() {
        if (dialog == null) {
            return false;
        }
        if (dialog.isShowing()) {
            return true;
        }
        return false;
    }
}
