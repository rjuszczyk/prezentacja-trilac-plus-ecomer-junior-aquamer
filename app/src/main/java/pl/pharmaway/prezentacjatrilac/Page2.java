package pl.pharmaway.prezentacjatrilac;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;

import pl.pharmaway.prezentacjatrilac.animation.AnimationOperator;
import pl.pharmaway.prezentacjatrilac.animation.DefaultAnimations;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;

public class Page2 extends FooterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View p2_1 = findViewById(R.id.p2_1);
        View p2_2 = findViewById(R.id.p2_2);
        View p2_3 = findViewById(R.id.p2_3);
        View p2_4 = findViewById(R.id.p2_4);
        View p2_5 = findViewById(R.id.p2_5);
        View p2_6 = findViewById(R.id.p2_6);
        View p2_7 = findViewById(R.id.p2_7);

        if (savedInstanceState == null) {

            Cancelable c = animateInCombined(500,
                    new Object[]{DefaultAnimations.beforeFadeAndScaleIn, DefaultAnimations.scaleIn, p2_1},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p2_4},
                    new Object[]{DefaultAnimations.beforeFadeAndScaleIn, DefaultAnimations.scaleIn, p2_2},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p2_5},
                    new Object[]{DefaultAnimations.beforeFadeAndScaleIn, DefaultAnimations.scaleIn, p2_3},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p2_6},
                    new Object[]{DefaultAnimations.beforeComeFromRight, DefaultAnimations.translateIn, p2_7}
            );

        } else {
            setVisible(
                    p2_1,
                    p2_2,
                    p2_3,
                    p2_4,
                    p2_5,
                    p2_6,
                    p2_7
            );
        }
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.page2;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page3.class;
    }



}
