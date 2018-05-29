package pl.pharmaway.prezentacjatrilac.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public abstract class BaseDialog extends DialogFragment {
    protected View mContentView;

    public abstract int getLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayoutId(), container);

        initView(inflater, container, savedInstanceState);
        return mContentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    public abstract void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
