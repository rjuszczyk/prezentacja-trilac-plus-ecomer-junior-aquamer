package pl.pharmaway.prezentacjatrilac;

import android.os.Bundle;
import android.view.View;

import pl.pharmaway.prezentacjatrilac.animation.DefaultAnimations;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;

public class Page8 extends FooterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View p8_1 = findViewById(R.id.p8_1);
        View p8_2 = findViewById(R.id.p8_2);

        if (savedInstanceState == null) {

            Cancelable c = animateInCombined(0,
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p8_1},
                    new Object[]{DefaultAnimations.beforeComeFromRight, DefaultAnimations.translateIn, p8_2}
            );
        } else {
            setVisible(
                    p8_1,
                    p8_2
                    );
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page8;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page9.class;
    }


}
