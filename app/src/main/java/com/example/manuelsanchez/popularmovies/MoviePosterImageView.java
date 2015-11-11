package com.example.manuelsanchez.popularmovies;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Manuel Sanchez on 11/10/15
 */
public class MoviePosterImageView extends ImageView {


    public MoviePosterImageView(Context context) {
        super(context);
    }

    public MoviePosterImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoviePosterImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int threeTwoHeight = MeasureSpec.getSize(widthMeasureSpec) * 2;
        int threeTwoHeightSpec = MeasureSpec.makeMeasureSpec(threeTwoHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, threeTwoHeightSpec);
    }
}
