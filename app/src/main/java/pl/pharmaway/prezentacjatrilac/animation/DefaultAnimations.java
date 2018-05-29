package pl.pharmaway.prezentacjatrilac.animation;

import android.view.View;
import android.view.ViewPropertyAnimator;

import pl.pharmaway.prezentacjatrilac.FooterActivity;


public class DefaultAnimations {
    public static FooterActivity.StateBeforeAnimation beforeFadeIn = new FooterActivity.StateBeforeAnimation() {
        @Override
        public void stateBefore(View view) {
            view.setAlpha(0);
        }
    };

    public static FooterActivity.StateBeforeAnimation beforeFadeAndScaleIn = new FooterActivity.StateBeforeAnimation() {
        @Override
        public void stateBefore(View view) {
            view.setAlpha(0);
            view.setScaleX(0.2f);
            view.setScaleY(0.2f);
        }
    };

    public static FooterActivity.StateBeforeAnimation beforeComeFromRight = new FooterActivity.StateBeforeAnimation() {
        @Override
        public void stateBefore(View view) {
            view.setTranslationX(view.getResources().getDisplayMetrics().density*1000);
            view.setAlpha(0);
        }
    };

    public static FooterActivity.StateBeforeAnimation beforeComeFromLeft = new FooterActivity.StateBeforeAnimation() {
        @Override
        public void stateBefore(View view) {
            view.setTranslationX(-view.getResources().getDisplayMetrics().density*1000);
            view.setAlpha(0);
        }
    };

    public static AnimationOperator scaleIn =  new AnimationOperator() {
        @Override
        public ViewPropertyAnimator apply(ViewPropertyAnimator animator) {
            return animator.alpha(1).scaleX(1).scaleY(1);
        }
    };

    public static AnimationOperator fadeIn =  new AnimationOperator() {
        @Override
        public ViewPropertyAnimator apply(ViewPropertyAnimator animator) {
            return animator.alpha(1);
        }
    };

    public static AnimationOperator translateIn =  new AnimationOperator() {
        @Override
        public ViewPropertyAnimator apply(ViewPropertyAnimator animator) {
            return animator.alpha(1).translationX(0);
        }
    };
}
