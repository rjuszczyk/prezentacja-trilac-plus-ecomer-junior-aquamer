package pl.pharmaway.prezentacjatrilac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;

import pl.pharmaway.prezentacjatrilac.animation.DefaultAnimations;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;

public class Page5 extends FooterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View p5_1 = findViewById(R.id.p5_1);
        View p5_2 = findViewById(R.id.p5_2);
        View p5_3 = findViewById(R.id.p5_3);
        View p5_4 = findViewById(R.id.p5_4);

        if (savedInstanceState == null) {

            Cancelable c = animateInCombined(0,
                    new Object[]{DefaultAnimations.beforeComeFromLeft, DefaultAnimations.translateIn, p5_1},
                    new Object[]{DefaultAnimations.beforeComeFromLeft, DefaultAnimations.translateIn, p5_2},
                    new Object[]{DefaultAnimations.beforeComeFromLeft, DefaultAnimations.translateIn, p5_3},
                    new Object[]{DefaultAnimations.beforeFadeIn, DefaultAnimations.fadeIn, p5_4}
            );
        } else {
            setVisible(
                    p5_1,
                    p5_2,
                    p5_3,
                    p5_4
            );
        }

        findViewById(R.id.button_sg) .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Page5.this, Page0.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page5;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page6.class;
    }
}
