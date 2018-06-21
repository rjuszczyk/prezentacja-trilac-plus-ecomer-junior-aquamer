package pl.pharmaway.prezentacjatrilac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;

import pl.pharmaway.prezentacjatrilac.animation.AnimationOperator;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;
import pl.pharmaway.prezentacjatrilac.mvp.fake.SimpleCancelable;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

public abstract class FooterActivity extends AppCompatActivity {

    protected AnimationOperator inAnimator =  new AnimationOperator() {
        @Override
        public ViewPropertyAnimator apply(ViewPropertyAnimator animator) {
            return animator.alpha(1);
        }
    };
    TimeSpendInApp timeSpendInApp;
    long startTime;
    @Nullable private View buttonNext;
    @Nullable private View buttonPrev;
    private boolean goToSummary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goToSummary = getIntent().getBooleanExtra("goToSummary", false);
        timeSpendInApp = new TimeSpendInApp(getSharedPreferences("appPrefs", Context.MODE_PRIVATE));

        getWindow().getDecorView().setSystemUiVisibility(
                SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        SYSTEM_UI_FLAG_FULLSCREEN |
                        SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        setContentView(getLayoutResourceId());

        buttonPrev = findViewById(R.id.button_prev);
        buttonNext = findViewById(R.id.button_next);

        if (buttonNext != null) {
            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNextClicked();
                }
            });
        }
        if (buttonPrev != null) {
            buttonPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPrevClicked();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
        long delta = System.currentTimeMillis() - startTime;
        timeSpendInApp.addTime(delta);
    }

    protected void onNextClicked() {
        Intent intent;
        if(!goToSummary) {
            intent = new Intent(this, getNextActivity());
        } else {
            intent = new Intent(this, Page6.class);
            intent.putExtra("goBackToMenu", true);
        }
        startActivity(intent);
    }

    protected void onPrevClicked() {
        onBackPressed();
    }

    protected long getDelay() {
        return 0;
    }

    protected long getDuration() {
        return 0;
    }

    protected Cancelable animateIn(long initialDelay, AnimationOperator animationOpeartor, View... paragraphs) {
        return animateIn(initialDelay, animationOpeartor, null, paragraphs);
    }
    protected Cancelable animateIn(final long initialDelay, AnimationOperator animationOpeartor, StateBeforeAnimation stateBeforeAnimation, final View... paragraphs) {
        final long delay = getDelay();
        final long duration = getDuration();

        final List<ViewPropertyAnimator> animations = new ArrayList<>();

        for (int i = 0; i < paragraphs.length; i++) {
            View paragraph = paragraphs[i];
            if(stateBeforeAnimation != null) {
                stateBeforeAnimation.stateBefore(paragraph);
            }
            ViewPropertyAnimator animation = animationOpeartor.apply(paragraph.animate())
                    .setDuration(duration)
                    .setStartDelay(initialDelay + i * delay + i * duration);
            animation.start();
            animations.add(animation);
        }

        return new Cancelable() {
            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public void cancel() {
                for (ViewPropertyAnimator animation : animations) {
                    animation.cancel();
                }
            }

            public int getAnimationLength() {
                return (int) (initialDelay + paragraphs.length*(delay+duration)+duration);
            }
        };
    }

    protected Cancelable animateInCombined(final long initialDelay, final Object[]... combined) {
        final long delay = getDelay();
        final long duration = getDuration();

        final List<ViewPropertyAnimator> animations = new ArrayList<>();

        for (int i = 0; i < combined.length; i++) {
            View paragraph = (View) combined[i][2];
            StateBeforeAnimation stateBeforeAnimation = (StateBeforeAnimation) combined[i][0];
            AnimationOperator animationOpeartor = (AnimationOperator) combined[i][1];
            if(stateBeforeAnimation != null) {
                stateBeforeAnimation.stateBefore(paragraph);
            }
            ViewPropertyAnimator animation = animationOpeartor.apply(paragraph.animate())
                    .setDuration(duration)
                    .setStartDelay(initialDelay + i * delay + i * duration);
            animation.start();
            animations.add(animation);
        }

        return new Cancelable() {
            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public void cancel() {
                for (ViewPropertyAnimator animation : animations) {
                    animation.cancel();
                }
            }

            public int getAnimationLength() {
                return (int) (initialDelay + combined.length*(delay+duration)+duration);
            }
        };
    }

    protected void setInvisible(View... paragraphs) {
        for (View paragraph : paragraphs) {
            paragraph.setAlpha(0);
        }
    }

    protected void setVisible(View... views) {
        for (View view : views) {
            view.setAlpha(1);
            view.setScaleX(1);
            view.setScaleY(1);
            view.setTranslationX(0);
        }
    }

    @LayoutRes
    protected abstract int getLayoutResourceId();

    protected abstract Class<?> getNextActivity();

    public interface StateBeforeAnimation {
        void stateBefore(View view);
    }
}
