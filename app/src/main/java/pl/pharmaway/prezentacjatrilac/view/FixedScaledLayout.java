package pl.pharmaway.prezentacjatrilac.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import pl.pharmaway.prezentacjatrilac.R;

public class FixedScaledLayout extends FrameLayout {
    private final static int FIXED_WIDTH_DEFAULT = 1024;
    private final static int FIXED_HEIGHT_DEFAULT = 1024;

    private final int fixedWidth;
    private final int fixedHeight;

    public FixedScaledLayout(@NonNull Context context) {
        super(context);

        fixedWidth = FIXED_WIDTH_DEFAULT;
        fixedHeight = FIXED_HEIGHT_DEFAULT;
    }

    public FixedScaledLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FixedScaledLayout, 0, 0);

        fixedWidth = (int)typedArray.getDimension(R.styleable.FixedScaledLayout_fixedWidth, FIXED_WIDTH_DEFAULT);
        fixedHeight = (int)typedArray.getDimension(R.styleable.FixedScaledLayout_fixedHeight, FIXED_HEIGHT_DEFAULT);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(
                MeasureSpec.makeMeasureSpec(fixedWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(fixedHeight, MeasureSpec.EXACTLY)
        );

        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);

        float widht = fixedWidth;
        float height = fixedHeight;

        float sW = (float) measuredWidth / widht;
        float sH = (float) measuredHeight / height;
        this.setPivotX(widht / 2);
        this.setPivotY(height / 2);

        float s = Math.min(sW, sH);
        this.setScaleX(s);
        this.setScaleY(s);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        FrameLayout.LayoutParams lpParams = (LayoutParams) params;
        lpParams.gravity = Gravity.CENTER;
        super.setLayoutParams(params);
    }
}
