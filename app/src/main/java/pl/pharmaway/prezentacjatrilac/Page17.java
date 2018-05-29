package pl.pharmaway.prezentacjatrilac;

import android.os.Bundle;
import android.view.View;

import pl.pharmaway.prezentacjatrilac.animation.DefaultAnimations;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;

public class Page17 extends FooterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View p = findViewById(R.id.p17_1);

        if (savedInstanceState == null) {

            Cancelable c = animateInCombined(500,
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
        return R.layout.page17;
    }

    @Override
    protected Class<?> getNextActivity() {
        return FormActivity.class;
    }
}
