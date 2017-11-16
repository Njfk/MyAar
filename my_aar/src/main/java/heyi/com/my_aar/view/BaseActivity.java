package heyi.com.my_aar.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

/**
 * Created by sev-14 on 2016/10/9.
 */

public class BaseActivity extends Activity {
    Boolean isFirst = true;
    Long firstTime;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    public void setToolbar(android.app.Fragment fragment, String tilte, int id){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(id, fragment, tilte);
        ft.commit();
    }
    @Override
    public void onBackPressed() {
        if (isFirst) {
            isFirst = false;
            firstTime = System.currentTimeMillis();
            Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
        } else {
            Long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2500) {
                firstTime = secondTime;
                Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
            } else {
                super.onBackPressed();
            }
        }
    }

}
