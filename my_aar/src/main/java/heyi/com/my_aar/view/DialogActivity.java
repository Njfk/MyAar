package heyi.com.my_aar.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;


/**
 * Created by sev-14 on 2016/12/21.
 */

public class DialogActivity extends AppCompatActivity {
    //    ActivityManager activityManager;
    ProgressDialog dialog;
    String uid = "";
    String name = "";
    String department_id = "";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {

        super.onCreate(savedInstanceState, persistentState);
//        activityManager = ActivityManager.getActivityManager();
//        activityManager.
        department_id = getSharedPreferences("User", MODE_PRIVATE).getString("department_id", "");
        uid = getSharedPreferences("User", MODE_PRIVATE).getString("id", "");
        name = getSharedPreferences("User", MODE_PRIVATE).getString("name", "");
    }


    public String getUid() {
        this.uid = getSharedPreferences("User", MODE_PRIVATE).getString("id", "");
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        this.name = getSharedPreferences("User", MODE_PRIVATE).getString("name", "");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment_id() {
        this.department_id = getSharedPreferences("User", MODE_PRIVATE).getString("department_id", "");

        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public void setToolbar(android.app.Fragment fragment, String tilte, int id) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(id, fragment, tilte);
        ft.commit();
    }
    private DialogInterface.OnKeyListener onKeyListener = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                dismissDialog();
            }
            return false;
        }
    };

    public void showDialog() {
        if (null == dialog) {
            dialog = ProgressDialog.show(this, "", "载入中...");
            dialog.setCancelable(false);
        } else {
            dialog.show();
        }
        dialog.setOnKeyListener(onKeyListener);
    }

    /**
     * dismiss dialog
     */
    public void dismissDialog() {
        if (isFinishing()) {
            return;
        }
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * cancel progress dialog if nesseary
     */
    @Override
    public void onBackPressed() {
        if (dialog != null && dialog.isShowing()) {
            dismissDialog();
        } else {
            super.onBackPressed();
        }
    }
}
