package heyi.com.my_aar.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by sev-14 on 2016/11/10.
 */

public class ActivityManager {
    private static Stack activityStack;
    private static ActivityManager instance;
    public static ActivityManager getActivityManager(){
        if(instance==null){
            instance=new ActivityManager();
        }
        return instance;
    }
    public void popActivity(){
        Activity activity= (Activity) activityStack.lastElement();
        if(activity!=null){
            activity.finish();
            activity=null;
        }
    }
    public void popActivity(Activity activity){
        if(activity!=null){
            activity.finish();
            activityStack.remove(activity);
            activity=null;
        }
    }
    public Activity currentActivity(){
        Activity activity= (Activity) activityStack.lastElement();
        return activity;
    }
    public void pushActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack();
        }
        activityStack.add(activity);
    }

    public void popAllActivityExceptOne(Class cls){
        while(true){
            Activity activity=currentActivity();
            if(activity==null){
                break;
            }
            if(activity.getClass().equals(cls) ){
                break;
            }
            popActivity(activity);
        }
    }
}
