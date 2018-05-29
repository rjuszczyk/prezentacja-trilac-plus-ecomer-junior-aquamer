package pl.pharmaway.prezentacjatrilac.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import pl.pharmaway.prezentacjatrilac.R;

/**
 * Created by Radek on 2017-12-29.
 */

public class FixedImageView extends android.support.v7.widget.AppCompatImageView {
    private int imageReference = 0;

    public FixedImageView(Context context) {
        super(context);
    }

    public FixedImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        handleArgs(attrs);
    }

    private void handleArgs(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.FixedImageView, 0, 0);


        imageReference = (int)typedArray.getResourceId(R.styleable.FixedImageView_imageReference, 0);
        setImageResource(imageReference);

        typedArray.recycle();
    }

    public FixedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handleArgs(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wh[] = DrawableDimensionGenerated.getDimensionForImageReosurce(imageReference);
        if(wh[0]==0 && wh[1] == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        int width = wh[0];
        int height = wh[1];

        width = (int) (getResources().getDisplayMetrics().density * width);
        height = (int) (getResources().getDisplayMetrics().density * height);

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        );
    }

}
