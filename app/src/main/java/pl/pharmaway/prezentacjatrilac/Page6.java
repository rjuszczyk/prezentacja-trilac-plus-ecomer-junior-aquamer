package pl.pharmaway.prezentacjatrilac;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class Page6 extends FooterActivity {

    boolean goBackToMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBackToMenu = getIntent().getBooleanExtra("goBackToMenu", false);
    }

    @Override
    public void onBackPressed() {
        if(!goBackToMenu) {
            super.onBackPressed();
        } else {
            Intent intent = new Intent(this, Page2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page6;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page7.class;
    }
}
