package pl.pharmaway.prezentacjatrilac;

import android.os.Bundle;
import android.view.View;

import pl.pharmaway.prezentacjatrilac.animation.DefaultAnimations;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;

public class Page12 extends FooterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View p12_1 = findViewById(R.id.p12_1);
        View p12_2 = findViewById(R.id.p12_2);
        View p12_3 = findViewById(R.id.p12_3);
        View p12_4 = findViewById(R.id.p12_4);

        if (savedInstanceState == null) {

            Cancelable c = animateInCombined(0,
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p12_1},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p12_2},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p12_3},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p12_4}
            );
        } else {
            setVisible(
                    p12_1,
                    p12_2,
                    p12_3,
                    p12_4
            );
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page12;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page13.class;
    }
}
