package pl.pharmaway.prezentacjatrilac;

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;

import pl.pharmaway.prezentacjatrilac.animation.DefaultAnimations;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;

public class Page3 extends FooterActivity {
    @Override
    protected long getDelay() {
        return 250;
    }
    @Override
    protected long getDuration() {
        return 750;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View p3_1 = findViewById(R.id.p3_1);
        View p3_2 = findViewById(R.id.p3_2);
        View p3_3 = findViewById(R.id.p3_3);
        View p3_4 = findViewById(R.id.p3_4);
        View p3_5 = findViewById(R.id.p3_5);
        View p3_6 = findViewById(R.id.p3_6);
        View p3_c1 = findViewById(R.id.p3_c1);
        View p3_c2 = findViewById(R.id.p3_c2);
        View p3_c3 = findViewById(R.id.p3_c3);
        View p3_c4 = findViewById(R.id.p3_c4);


        if (savedInstanceState == null) {

            Cancelable c = animateInCombined(500,
                    new Object[]{DefaultAnimations.beforeComeFromRight, DefaultAnimations.translateIn, p3_1},
                    new Object[]{DefaultAnimations.beforeComeFromRight, DefaultAnimations.translateIn, p3_2},
                    new Object[]{DefaultAnimations.beforeComeFromRight, DefaultAnimations.translateIn, p3_3},
                    new Object[]{DefaultAnimations.beforeComeFromRight, DefaultAnimations.translateIn, p3_4},
                    new Object[]{DefaultAnimations.beforeComeFromRight, DefaultAnimations.translateIn, p3_5},
                    new Object[]{DefaultAnimations.beforeFadeAndScaleIn, DefaultAnimations.scaleIn, p3_6},
                    new Object[]{DefaultAnimations.beforeFadeAndScaleIn, DefaultAnimations.scaleIn, p3_c1},
                    new Object[]{DefaultAnimations.beforeFadeAndScaleIn, DefaultAnimations.scaleIn, p3_c2},
                    new Object[]{DefaultAnimations.beforeFadeAndScaleIn, DefaultAnimations.scaleIn, p3_c3},
                    new Object[]{DefaultAnimations.beforeFadeAndScaleIn, DefaultAnimations.scaleIn, p3_c4}
            );
        } else {
            setVisible(
                    p3_1,
                    p3_2,
                    p3_3,
                    p3_4,
                    p3_5,
                    p3_6,
                    p3_c1,
                    p3_c2,
                    p3_c3,
                    p3_c4

                    );
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page3;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page4.class;
    }


}
