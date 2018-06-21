package pl.pharmaway.prezentacjatrilac;

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;

import pl.pharmaway.prezentacjatrilac.animation.AnimationOperator;
import pl.pharmaway.prezentacjatrilac.animation.DefaultAnimations;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;

public class Page4 extends FooterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View p4_1 = findViewById(R.id.p4_1);
        View p4_2 = findViewById(R.id.p4_2);
        View p4_3 = findViewById(R.id.p4_3);
        View p4_4 = findViewById(R.id.p4_4);
        View p4_5 = findViewById(R.id.p4_5);
        View p4_6 = findViewById(R.id.p4_6);
        View p4_7 = findViewById(R.id.p4_7);
        View p4_8 = findViewById(R.id.p4_8);
        View p4_9 = findViewById(R.id.p4_9);

        if (savedInstanceState == null) {
            Cancelable c = animateInCombined(0,
                    new Object[]{DefaultAnimations.beforeFadeAndScaleIn, DefaultAnimations.scaleIn, p4_1},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p4_2},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p4_3},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p4_4},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p4_5},
                    new Object[]{DefaultAnimations.beforeFadeAndScaleIn, DefaultAnimations.scaleIn, p4_6},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p4_7},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p4_8},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p4_9}
            );
        } else {
            setVisible(
                    p4_1,
                    p4_2,
                    p4_3,
                    p4_4,
                    p4_5,
                    p4_6,
                    p4_7,
                    p4_8,
                    p4_9
            );
        }
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.page4;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page5.class;
    }
}
