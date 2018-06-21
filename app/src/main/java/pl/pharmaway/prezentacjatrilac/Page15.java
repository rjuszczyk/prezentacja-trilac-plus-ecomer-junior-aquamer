package pl.pharmaway.prezentacjatrilac;

import android.os.Bundle;
import android.view.View;

import pl.pharmaway.prezentacjatrilac.animation.DefaultAnimations;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;

public class Page15 extends FooterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View p = findViewById(R.id.p15_1);

        if (savedInstanceState == null) {

            Cancelable c = animateInCombined(0,
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p}
            );
        } else {
            setVisible(
                    p
            );
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page15;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page16.class;
    }
}
