package com.wind.expandabletextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wind on 2016-07-07.
 */
public class ExpandableView extends LinearLayout {
    private static final String TAG = ExpandableView.class.getSimpleName();
    private TextView tvTitle;
    private TextView tvText;
    private TextView tvShowMoreLess;
    private boolean bShow = false;
    private String showMore;
    private String showLess;
    private int minTextLines;
    private int maxTextLines;

    private static final int DEFAULT_MIN_LINES = Integer.MAX_VALUE;
    private static final int DEFAULT_MAX_LINES = Integer.MAX_VALUE;
    private static final int DEFAULT_MARGIN = 0;
    private static final int DEFAULT_SIZE = 40;
    private static final int DEFAULT_COLOR = R.color.colorAccent;

    public ExpandableView(Context context) {
        super(context);
        init(context, null);
    }

    public ExpandableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        Log.d(TAG, "init");
        if(attrs != null){

            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableView);

            //TITLE
            String titleText = typedArray.getString(R.styleable.ExpandableView_titleText);
            int titleSize = typedArray.getDimensionPixelSize(R.styleable.ExpandableView_titleSize, DEFAULT_SIZE);
            int titleColor = typedArray.getColor(R.styleable.ExpandableView_titleColor, ContextCompat.getColor(context, DEFAULT_COLOR));

            //TEXT BODY
            int marginBetweenTitleAndTextBody = typedArray.getDimensionPixelSize(R.styleable.ExpandableView_marginBetweenTitleAndTextBody, DEFAULT_MARGIN);
            String textBodyText = typedArray.getString(R.styleable.ExpandableView_textBodyText);
            int textBodySize = typedArray.getDimensionPixelSize(R.styleable.ExpandableView_textBodySize, DEFAULT_SIZE);
            int textBodyColor = typedArray.getColor(R.styleable.ExpandableView_textBodyColor, ContextCompat.getColor(context, DEFAULT_COLOR));
            minTextLines = typedArray.getInt(R.styleable.ExpandableView_textBodyMinLines, DEFAULT_MIN_LINES);
            maxTextLines = typedArray.getInt(R.styleable.ExpandableView_textBodyMaxLines, DEFAULT_MAX_LINES);

            //SHOW LESS
            int marginBetweenTextAndShowLess = typedArray.getDimensionPixelSize(R.styleable.ExpandableView_marginBetweenTextBodyAndShowMoreLess, DEFAULT_MARGIN);
            int showSize = typedArray.getDimensionPixelSize(R.styleable.ExpandableView_showMoreLessSize, DEFAULT_SIZE);
            int showColor = typedArray.getColor(R.styleable.ExpandableView_showMoreLessColor, ContextCompat.getColor(context, DEFAULT_COLOR));
            showMore = typedArray.getString(R.styleable.ExpandableView_showMoreText);
            showLess = typedArray.getString(R.styleable.ExpandableView_showLessText);

            typedArray.recycle();

            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layoutInflater.inflate(R.layout.content_custom_layout, this, true);

            tvTitle = (TextView) findViewById(R.id.tv_title);
            tvText = (TextView) findViewById(R.id.tv_text_body);
            tvShowMoreLess = (TextView) findViewById(R.id.tv_show_more_less);

            if(!(titleText == null) && !titleText.isEmpty()){
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText(titleText);
                tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
                tvTitle.setTextColor(titleColor);
            }

            tvText.setText(textBodyText);
            tvText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textBodySize);
            tvText.setTextColor(textBodyColor);
            LayoutParams params = (LayoutParams) tvText.getLayoutParams();
            params.topMargin = marginBetweenTitleAndTextBody;
            tvText.setLayoutParams(params);

            tvText.setMaxLines(minTextLines);

            tvShowMoreLess.setTextSize(TypedValue.COMPLEX_UNIT_PX, showSize);
            tvShowMoreLess.setTextColor(showColor);
            params = (LayoutParams) tvShowMoreLess.getLayoutParams();
            params.topMargin = marginBetweenTextAndShowLess;
            tvShowMoreLess.setLayoutParams(params);

            tvShowMoreLess.setText(showMore);
            tvShowMoreLess.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkShowStatus();
                }
            });
        }
    }

    private boolean isEllipsised(TextView tv){
        Layout l = tv.getLayout();
        if (l != null) {
            int lines = l.getLineCount();
            if (lines > 0) {
                if (l.getEllipsisCount(lines - 1) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkShowStatus(){
        if(bShow){
            tvText.setMaxLines(minTextLines);
            tvShowMoreLess.setText(showMore);
        }else{
            tvText.setMaxLines(maxTextLines);
            tvShowMoreLess.setText(showLess);
        }
        bShow = !bShow;
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
////        Log.e(TAG, "widthMeasureSpec = " + widthMeasureSpec);
////        Log.e(TAG, "heightMeasureSpec = " + heightMeasureSpec);
//
//        int desiredWidth = 100;
//        int desiredHeight = 100;
//
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        int width;
//        int height;
//
////        //Measure Width
////        if (widthMode == MeasureSpec.EXACTLY) {
////            //Must be this size
////            width = widthSize;
////        } else if (widthMode == MeasureSpec.AT_MOST) {
////            //Can't be bigger than...
////            width = Math.min(desiredWidth, widthSize);
////        } else {
////            //Be whatever you want
////            width = desiredWidth;
////        }
////
////        //Measure Height
////        if (heightMode == MeasureSpec.EXACTLY) {
////            //Must be this size
////            height = heightSize;
////        } else if (heightMode == MeasureSpec.AT_MOST) {
////            //Can't be bigger than...
////            height = Math.min(desiredHeight, heightSize);
////        } else {
////            //Be whatever you want
////            height = desiredHeight;
////        }
//
//        Log.d(TAG, "width = " + widthSize);
//        Log.d(TAG, "height = " + heightSize);
//
//        //MUST CALL THIS
//        setMeasuredDimension(widthSize, heightSize);
//    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        Log.d(TAG, "tvTitle.getHeight() = " + tvTitle.getHeight());
        Log.d(TAG, "tvText.getHeight() = " + tvText.getHeight());
        Log.d(TAG, "tvShowMoreLess.getHeight() = " + tvShowMoreLess.getHeight());

        Log.d(TAG, "tvText.getLineCount() = " + tvText.getLineCount());

        if(isEllipsised(tvText)){
            tvShowMoreLess.setVisibility(View.VISIBLE);
        }
    }

    public void setTitleSize(int sp){
        if(tvTitle != null){
            tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
        }
    }

    public void setTitleText(String text){
        if(tvTitle != null && text != null){
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(text);
        }
    }

    public void setTitleColor(int color){
        if(tvTitle != null){
            tvTitle.setTextColor(color);
        }
    }

    public void setMarginBetweenTitleAndTextBody(int dp){
        LayoutParams params = (LayoutParams) tvText.getLayoutParams();
        params.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, dp, getResources().getDisplayMetrics());
        tvText.setLayoutParams(params);
    }

    public void setTextBodySize(int sp){
        if(tvText != null){
            tvText.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
        }
    }

    public void setTextBodyText(String text) {
        if(tvText != null && text != null){
            tvText.setText(text);
        }
    }

    public void setTextBodyColor(int color){
        if(tvText != null){
            tvText.setTextColor(color);
        }
    }

    public void setTextBodyMinLines(int lines) {
        minTextLines = lines;
        if(tvText != null){
            tvText.setMaxLines(lines);
        }
        checkShowStatus();
    }

    public void setTextBodyMaxLines(int lines) {
        maxTextLines = lines;
        if(tvText != null){
            tvText.setMaxLines(lines);
        }
        checkShowStatus();
    }

    public void setMarginBetweenTextBodyAndShowMoreLess(int dp){
        LayoutParams params = (LayoutParams) tvShowMoreLess.getLayoutParams();
        params.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, dp, getResources().getDisplayMetrics());
        tvShowMoreLess.setLayoutParams(params);
    }

    public void setShowMoreLessSize(int sp){
        if(tvShowMoreLess != null){
            tvShowMoreLess.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
        }
    }

    public void setShowMoreLessColor(int color){
        if(tvShowMoreLess != null){
            tvShowMoreLess.setTextColor(color);
        }
    }

    public void setShowMoreText(String text) {
        showMore = text;
        checkShowStatus();
    }

    public void setShowLessText(String text) {
        showLess = text;
        checkShowStatus();
    }

}


