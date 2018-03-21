package heyi.com.pda.net.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import heyi.com.pda.module.index.LoginActivity;
import heyi.com.pda.utils.ActivityStack;

/**
 * Created by sev-14 on 2017/11/14.
 */

public abstract class ILoadSucess {
    abstract void net_error();

    abstract void gson_error();

    abstract void showdialog();

    abstract void dismissdialog();

    public void reLogin(Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setMessage("token失效请重新登录")
                .setNegativeButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityStack.getInstance().removeAllActivityExcept(LoginActivity.class);
                    }
                }).create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}
