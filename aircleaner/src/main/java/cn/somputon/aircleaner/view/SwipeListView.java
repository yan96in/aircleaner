package cn.somputon.aircleaner.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * TODO: document your custom view class.
 */
public class SwipeListView extends ListView {
    private Boolean isHorizontal;
    private View preItemView;
    private View currentItemView;
    private float firstX;
    private float firstY;
    private int rightViewWidth = 300;
    private final int DURATION = 100;
    private final int DURATIONSTEP = 10;
    private boolean isShown;

    private boolean isFooterCanSwipe = false;
    private boolean isHeaderCanSwipe = false;

    public SwipeListView(Context context) {
        super(context);
    }

    public SwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        float lastX = event.getX();
        float lastY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isHorizontal = null;
                firstX = lastX;
                firstY = lastY;
                int motionPosition = pointToPosition((int) firstX, (int) firstY);
                if (motionPosition >= 0) {
                    View currentItemView = getChildAt(motionPosition - getFirstVisiblePosition());
                    preItemView = currentItemView;
                    this.currentItemView = currentItemView;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = lastX - firstX;
                float dy = lastY - firstY;
                if (Math.abs(dx) >= 5 && Math.abs(dy) >= 5) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (isShown && (preItemView != currentItemView || isHitCurItemLeft(lastX))) {
                    hiddenRight(preItemView);
                }
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    private boolean isHitCurItemLeft(float x) {
        return x < getWidth() - rightViewWidth;
    }

    private boolean judgeScrollDirection(float dx, float dy) {
        boolean canJudge = true;
        if (Math.abs(dx) > 30 && Math.abs(dx) > 2 * Math.abs(dy)) {
            isHorizontal = true;
        } else if (Math.abs(dy) > 30 && Math.abs(dy) > 2 * Math.abs(dy)) {
            isHorizontal = false;
        } else {
            canJudge = false;

        }
        return canJudge;
    }

    private boolean judgeFooterView(float posX, float posY) {
        if (isFooterCanSwipe) {
            return true;
        }
        int selectPos = pointToPosition((int) posX, ((int) posY));
        if (selectPos >= (getCount() - getFooterViewsCount())) {
            return false;
        }
        return true;
    }

    private boolean judgeHeaderView(float posX, float posY) {
        if (isFooterCanSwipe) {
            return true;
        }
        int selectPos = pointToPosition((int) posX, ((int) posY));
        if (selectPos >= (getCount() - getHeaderViewsCount())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float lastX = ev.getX();
        float lastY = ev.getY();
        if (!judgeFooterView(firstX, firstY) || !judgeHeaderView(firstX, firstY)) {
            return super.onTouchEvent(ev);
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = lastX - firstX;
                float dy = lastY - firstY;
                if (isHorizontal == null) {
                    if (!judgeScrollDirection(dx, dy)) {
                        break;
                    }
                }
                if (isHorizontal) {
                    if (isShown && preItemView != currentItemView) {
                        /**
                         * 情况二：
                         * <p>
                         * 一个Item的右边布局已经显示，
                         * <p>
                         * 这时候左右滑动另外一个item,那个右边布局显示的item隐藏其右边布局
                         * <p>
                         * 向左滑动只触发该情况，向右滑动还会触发情况五
                         */
                        hiddenRight(preItemView);
                    }
                    if (isShown && preItemView == currentItemView) {
                        //// TODO: 2015/12/12  becare
                        dx -= rightViewWidth;
                    }
                    if (dx < 0 && dx > -rightViewWidth) {
                        currentItemView.scrollTo((int) (-dx), 0);
                    }
                    return true;
                } else {
                    if (isShown) {
                        /**
                         * 情况三：
                         * <p>
                         * 一个Item的右边布局已经显示，
                         * <p>
                         * 这时候上下滚动ListView,那么那个右边布局显示的item隐藏其右边布局
                         */
                        hiddenRight(preItemView);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                clearPressedState();
                if (isShown) {
                    /**
                     * 情况四：
                     * <p>
                     * 一个Item的右边布局已经显示，
                     * <p>
                     * 这时候左右滑动当前一个item,那个右边布局显示的item隐藏其右边布局
                     */
                    hiddenRight(preItemView);
                }
                if (isHorizontal != null && isHorizontal) {
                    if (firstX - lastX > rightViewWidth) {
                        showRight(currentItemView);
                    } else {
                        /**
                         * 情况五：
                         * <p>
                         * 向右滑动一个item,且滑动的距离超过了右边View的宽度的一半，隐藏之。
                         */
                        hiddenRight(currentItemView);
                    }
                    MotionEvent obtain = MotionEvent.obtain(ev);
                    obtain.setAction(MotionEvent.ACTION_CANCEL);
                    super.onTouchEvent(obtain);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }


    private void clearPressedState() {
        currentItemView.setPressed(false);
        setPressed(false);
        refreshDrawableState();
    }

    private void showRight(View view) {
        Message msg = new MoveHandler().obtainMessage();
        msg.obj = view;
        msg.arg1 = view.getScrollX();
        msg.arg2 = rightViewWidth;
        msg.sendToTarget();
        isShown = true;
    }

    private void hiddenRight(View view) {
        if (currentItemView == null) {
            return;
        }
        Message msg = new MoveHandler().obtainMessage();
        msg.obj = view;
        msg.arg1 = view.getScrollX();
        msg.arg2 = 0;
        msg.sendToTarget();
        isShown = false;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @SuppressLint("HandlerLeak")
    private class MoveHandler extends Handler {
        int stepX = 0;
        int fromX;
        int toX;
        View view;
        private boolean isInAnimation = false;

        private void animatioOver() {
            isInAnimation = false;
            stepX = 0;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (stepX == 0) {
                if (isInAnimation) {
                    return;
                }
                isInAnimation = true;
                view = ((View) msg.obj);
                fromX = msg.arg1;
                toX = msg.arg2;
                stepX = ((int) ((toX - fromX) * DURATIONSTEP * 1.0 / DURATION));
                if (stepX < 0 && stepX > -1) {
                    stepX = -1;
                } else if (stepX > 0 && stepX < 1) {
                    stepX = 1;
                }
                if (Math.abs(toX-fromX)<10) {
                    view.scrollTo(toX,0);
                    animatioOver();
                    return;
                }
            }
            fromX+=stepX;
            boolean isLastStep=(stepX>0&&fromX>toX)||(stepX<0&&fromX<toX);
            if (isLastStep) {
                fromX=toX;
            }
            view.scrollTo(fromX,0);
            invalidate();
            if (!isLastStep) {
                this.sendEmptyMessageDelayed(0,DURATIONSTEP);
            }else {
                animatioOver();
            }
        }
    }

    public int getRightViewWidth() {
        return rightViewWidth;
    }

    public void setRightViewWidth(int rightViewWidth) {
        this.rightViewWidth = rightViewWidth;
    }



    public void setFooterCanSwipe(boolean isFooterCanSwipe) {
        this.isFooterCanSwipe = isFooterCanSwipe;
    }

    public void setHeaderCanSwipe(boolean isHeaderCanSwipe) {
        this.isHeaderCanSwipe = isHeaderCanSwipe;
    }

    public void setFooterAndHeaderCanSwipe(boolean footer,boolean header){
        isFooterCanSwipe=footer;
        isHeaderCanSwipe=header;
    }
}
