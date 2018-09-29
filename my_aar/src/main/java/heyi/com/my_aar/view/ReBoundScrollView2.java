package suzi.com.suzi_game;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;

/**
 * Created by sev-14 on 2018/9/26.
 */

public class ReBoundScrollView2 extends HorizontalScrollView{

    private static final float MOVE_DELAY = 0.3f;//当拉出屏幕时的拖拽系数
    private static final int ANIM_TIME = 300;//回弹耗时
    private static final int FLING = 2;//fling 系数

    private View childView;
    private boolean havaMoved;

    private Rect originalRect = new Rect();

    private float startY;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            childView = getChildAt(0);
        }
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 2);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (childView == null)
            return;

        originalRect.set(childView.getLeft(), childView.getTop(),
                childView.getRight(), childView.getBottom());
    }


    public ReBoundScrollView2(Context context, AttributeSet attrs,
                              int defStyle) {
        super(context, attrs, defStyle);
    }

    public ReBoundScrollView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReBoundScrollView2(Context context) {
        super(context);
    }


    /**
     * 在触摸事件中, 处理上拉和下拉的逻辑
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (childView == null) {
            return super.dispatchTouchEvent(ev);
        }

        int action = ev.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (!havaMoved)
                    break;

                TranslateAnimation anim = new TranslateAnimation(
                        0, 0,childView.getTop(), originalRect.top);
                anim.setDuration(ANIM_TIME);
                childView.startAnimation(anim);
                // 将标志位设回false
                havaMoved = false;
                resetViewLayout();

                break;
            case MotionEvent.ACTION_MOVE:

                float nowY = ev.getY();
                int deltaY = (int) (nowY - startY);
                int offset = (int) (deltaY * MOVE_DELAY);
                childView.layout(originalRect.left , originalRect.top+ offset,
                        originalRect.right, originalRect.bottom+offset);

                havaMoved = true;

                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    public void resetViewLayout() {
        childView.layout(originalRect.left, originalRect.top,
                originalRect.right, originalRect.bottom);
    }


}
