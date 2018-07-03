package pl.pharmaway.prezentacjatrilac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Page0 extends FooterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FirstChoice firstChoice = new FirstChoice(getSharedPreferences("appPrefs", Context.MODE_PRIVATE));
        findViewById(R.id.trilac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstChoice.setFirstChoice("trilac");
                Intent intent = new Intent(Page0.this, Page1.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.ecomer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstChoice.setFirstChoice("ecomer");
                Intent intent = new Intent(Page0.this, Page6.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.aquamer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstChoice.setFirstChoice("aquamer");
                Intent intent = new Intent(Page0.this, Page13.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page_summary;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page1.class;
    }
}
