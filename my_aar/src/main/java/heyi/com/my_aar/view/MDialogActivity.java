package heyi.com.my_aar.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * Created by sev-14 on 2017/11/27.
 */

public class MDialogActivity extends DialogActivity {
    LoadingDialog myDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    public void showDialog(String msg) {

        if (myDialog == null) {
            myDialog = new LoadingDialog(this);
            myDialog.showDialog(msg);
        } else {
            myDialog.showDialog(msg);
        }
    }

    public void dismissDialog() {
        if (myDialog == null) {
            myDialog = new LoadingDialog(this);
            myDialog.dismissDialog();
        } else {
            myDialog.dismissDialog();
        }

    }

    public boolean isShowing() {
        if (myDialog == null) {
            return false;
        }
        if (myDialog.isShowing()) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (isShowing()) {
            dismissDialog();
        } else {
            super.onBackPressed();
        }
    }
}
