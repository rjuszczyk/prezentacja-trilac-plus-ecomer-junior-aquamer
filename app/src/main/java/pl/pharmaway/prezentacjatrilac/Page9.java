package pl.pharmaway.prezentacjatrilac;

import android.os.Bundle;
import android.view.View;

import pl.pharmaway.prezentacjatrilac.animation.DefaultAnimations;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;

public class Page9 extends FooterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View p9_1 = findViewById(R.id.p9_1);
        View p9_2 = findViewById(R.id.p9_2);

        if (savedInstanceState == null) {

            Cancelable c = animateInCombined(500,
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p9_1},
                    new Object[]{DefaultAnimations.beforeComeFromRight, DefaultAnimations.translateIn, p9_2}
            );
        } else {
            setVisible(
                    p9_1,
                    p9_2
            );
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page9;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page10.class;
    }

}
