package com.kikt.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaiJL on 2016/3/18.
 * Simple Custom Rating bar
 */
public class CustomRatingBar extends ViewGroup {

    public static final int STAR_EMPTY = R.drawable.star_empty;
    public static final int STAR_HALF = R.drawable.star_half;
    public static final int STAR_FULL = R.drawable.star_full;
    private final Context mContext;
    protected int mMaxStar;
    private int mPadding;
    protected int mStarWidth;
    protected int mStarHeight;
    protected LayoutParams childParams;
    protected float stars;
    private float lastStars;
    protected float mMinStar;

    protected int mEmptyStar;
    protected int mFullStar;
    protected int mHalfStar;

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
        mMaxStar = a.getInteger(R.styleable.RB_maxStar, 5);
        mPadding = (int) a.getDimension(R.styleable.RB_padding, 10);
        mStarWidth = (int) a.getDimension(R.styleable.RB_starWidth, 40);
        mStarHeight = (int) a.getDimension(R.styleable.RB_starHeight, 40);
        mMinStar = a.getFloat(R.styleable.RB_minStar, 0);
        stars = a.getFloat(R.styleable.RB_currentStar, 0) * 2;
        mEmptyStar = a.getResourceId(R.styleable.RB_emptyStar, STAR_EMPTY);
        mHalfStar = a.getResourceId(R.styleable.RB_halfStar, STAR_HALF);
        mFullStar = a.getResourceId(R.styleable.RB_fullStar, STAR_FULL);
        isCanChange = a.getBoolean(R.styleable.RB_canChange, true);

        for (int i = 0; i < mMaxStar; i++) {
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
        childParams.height = mStarHeight;
        imageView.setLayoutParams(childParams);
        return imageView;
    }

    private boolean isCanChange;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (isCanChange) {
                    float x = event.getX();
                    int current = checkX(x);
                    stars = fixStars(current);
                    checkState();
                    break;
                } else {
                    return false;
                }
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
        int stars = (int) this.stars;
        if (stars % 2 == 0) {
            for (int i = 0; i < mMaxStar; i++) {
                if (i < stars / 2) {
                    setFullView(list.get(i));
                } else {
                    setEmptyView(list.get(i));
                }
            }
        } else {
            for (int i = 0; i < mMaxStar; i++) {
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
        view.setImageResource(mEmptyStar);
    }

    protected void setHalfView(ImageView view) {
        view.setImageResource(mHalfStar);
    }

    protected void setFullView(ImageView view) {
        view.setImageResource(mFullStar);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setView();
    }

    public int getMax() {
        return mMaxStar;
    }

    public void setMax(int mCount) {
        this.mMaxStar = mCount;
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

    public int getStarHeight() {
        return mStarHeight;
    }

    public void setStarHeight(int mStarHeight) {
        this.mStarHeight = mStarHeight;
    }

    public float getMinStar() {
        return mMinStar;
    }

    public void setMinStar(float mMinStar) {
        this.mMinStar = mMinStar;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public float getStars() {
        return stars;
    }

    public void setCanChange(boolean canChange) {
        isCanChange = canChange;
    }

    public boolean isCanChange() {
        return isCanChange;
    }

    private float fixStars(int current) {
        if (current > mMaxStar * 2) {
            return mMaxStar * 2;
        } else if (current < mMinStar * 2) {
            return mMinStar * 2;
        }
        return current;
    }

    private int checkX(float x) {
        int width = getWidth();
        int per = width / mMaxStar / 2;
        return (int) (x / per) + 1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();

        int width = 0;
        int height = 0;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();
            width += measuredWidth;
            height = measuredHeight;

            if (i != childCount - 1) {
                width += mPadding;
            }
        }
        setMeasuredDimension(width, height);
    }

    @Override
    public MarginLayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        t = 0;
        l = 0;
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
