package pl.pharmaway.prezentacjatrilac.animation;

import android.view.ViewPropertyAnimator;

public interface AnimationOperator {
    ViewPropertyAnimator apply(ViewPropertyAnimator animator);
}
