package pl.pharmaway.prezentacjatrilac;

import android.os.Bundle;

public class Page1 extends FooterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page1;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page2.class;
    }
}
