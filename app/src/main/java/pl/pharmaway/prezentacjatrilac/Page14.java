package pl.pharmaway.prezentacjatrilac;

import android.os.Bundle;
import android.view.View;

import pl.pharmaway.prezentacjatrilac.animation.DefaultAnimations;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;

public class Page14 extends FooterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View p14_1 = findViewById(R.id.p14_1);
        View p14_2 = findViewById(R.id.p14_2);
        View p14_3 = findViewById(R.id.p14_3);
        View p14_4 = findViewById(R.id.p14_4);
        View p14_5 = findViewById(R.id.p14_5);
        View p14_6 = findViewById(R.id.p14_6);
        View p14_7 = findViewById(R.id.p14_7);

        if (savedInstanceState == null) {

            Cancelable c = animateInCombined(500,
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p14_1},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p14_2},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p14_3},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p14_4},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p14_5},
                    new Object[]{DefaultAnimations.beforeComeFromRight, DefaultAnimations.translateIn, p14_6},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p14_7}

            );
        } else {
            setVisible(
                    p14_1,
                    p14_2,
                    p14_3,
                    p14_4,
                    p14_5,
                    p14_6,
                    p14_7
            );
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page14;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page15.class;
    }
}
