package com.kikt.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaiJL on 2016/3/18.
 * 自定义的RatingBar
 */
public class CustomRatingBar extends ViewGroup {

    private final Context mContext;
    protected int mCount;
    private int mPadding;
    protected int mStarWidth;
    protected int mStartHeight;
    protected LayoutParams childParams;
    protected MarginLayoutParams mParams;
    protected float stars;
    private float lastStars;
    protected float mMinStar;

    public CustomRatingBar(Context context) {
        this(context, null);
    }

    public CustomRatingBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.RB);
        initView(a);
        a.recycle();
    }

    private void initView(TypedArray a) {
        mCount = a.getInteger(R.styleable.RB_count, 0);
        mPadding = (int) a.getDimension(R.styleable.RB_padding, 10);
        mStarWidth = (int) a.getDimension(R.styleable.RB_starWidth, 40);
        mStartHeight = (int) a.getDimension(R.styleable.RB_starHeight, 40);
        mMinStar = a.getFloat(R.styleable.RB_minStar, 0);
        stars = a.getFloat(R.styleable.RB_currentCount, 0) * 2;
        for (int i = 0; i < mCount; i++) {
            ImageView child = createChild();
            addView(child);
            list.add(child);
        }
    }

    List<ImageView> list = new ArrayList<ImageView>();

    private ImageView createChild() {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.star_empty);
        childParams = generateDefaultLayoutParams();
        childParams.width = mStarWidth;
        childParams.height = mStartHeight;
        imageView.setLayoutParams(childParams);
        return imageView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                int current = checkX(x);
                stars = fixStars(current);
                checkState();
                break;
        }
        return true;
    }

    public OnStarChangeListener getOnStarChangeListener() {
        return onStarChangeListener;
    }

    public void setOnStarChangeListener(OnStarChangeListener onStarChangeListener) {
        this.onStarChangeListener = onStarChangeListener;
    }

    public void removeOnStarChangeLisetener() {
        this.onStarChangeListener = null;
    }

    public interface OnStarChangeListener {
        void onStarChange(CustomRatingBar ratingBar, float star);
    }

    private OnStarChangeListener onStarChangeListener;

    private void checkState() {
        if (lastStars != stars) {
            lastStars = stars;
            if (onStarChangeListener != null) {
                onStarChangeListener.onStarChange(this, stars / 2);
            }
            setView();
        }
    }

    private void setView() {
        if (stars < mMinStar * 2) {
            stars = mMinStar * 2;
        }
        Log.d("CustomRatingBar", "stars:" + stars);
        int stars = (int) this.stars;
        if (stars % 2 == 0) {
            for (int i = 0; i < mCount; i++) {
                if (i < stars / 2) {
                    setFullView(list.get(i));
                } else {
                    setEmptyView(list.get(i));
                }
            }
        } else {
            for (int i = 0; i < mCount; i++) {
                if (i < stars / 2) {
                    setFullView(list.get(i));
                } else if (i == stars / 2) {
                    setHalfView(list.get(i));
                } else {
                    setEmptyView(list.get(i));
                }
            }
        }
    }

    protected void setEmptyView(ImageView view) {
        view.setImageResource(R.drawable.star_empty);
    }

    protected void setHalfView(ImageView view) {
        view.setImageResource(R.drawable.star_half);
    }

    protected void setFullView(ImageView view) {
        view.setImageResource(R.drawable.star_full);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setView();
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
    }

    public int getPadding() {
        return mPadding;
    }

    public void setPadding(int mPadding) {
        this.mPadding = mPadding;
    }

    public int getStarWidth() {
        return mStarWidth;
    }

    public void setStarWidth(int mStarWidth) {
        this.mStarWidth = mStarWidth;
    }

    public int getStartHeight() {
        return mStartHeight;
    }

    public void setStartHeight(int mStartHeight) {
        this.mStartHeight = mStartHeight;
    }

    public float getMinStar() {
        return mMinStar;
    }

    public void setMinStar(float mMinStar) {
        this.mMinStar = mMinStar;
    }

    private float fixStars(int current) {
        if (current > mCount * 2) {
            return mCount * 2;
        } else if (current < mMinStar * 2) {
            return mMinStar * 2;
        }
        return current;
    }

    private int checkX(float y) {
        int width = getWidth();
        int per = width / mCount / 2;
        return (int) (y / per);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mParams = (MarginLayoutParams) getLayoutParams();
        int childCount = getChildCount();

        int width = 0;
        int height = 0;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();
            width += measuredWidth;
            height = measuredHeight;

            if (i != childCount - 1) {
                width += mPadding;
            }
        }
        width += (mParams.leftMargin + mParams.rightMargin);
        height += (mParams.topMargin + mParams.bottomMargin);
        setMeasuredDimension(width, height);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            r = l + child.getMeasuredWidth();
            b = t + child.getMeasuredHeight();
            child.layout(l, t, r, b);
            l = r + mPadding;
        }
    }
}
