package pl.pharmaway.prezentacjatrilac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pl.pharmaway.prezentacjatrilac.animation.DefaultAnimations;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;

public class PageSummary extends FooterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View p = findViewById(R.id.all);

           p.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   startActivity(LoadingActivity.class);
               }
           });
    }

    @Override
    public void onBackPressed() {
        startActivity(LoadingActivity.class);
        super.onBackPressed();
    }

    private void startActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page_summary;
    }

    @Override
    protected Class<?> getNextActivity() {
        return LoadingActivity.class;
    }
}
