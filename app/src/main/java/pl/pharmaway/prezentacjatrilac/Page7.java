package pl.pharmaway.prezentacjatrilac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Page7 extends FooterActivity {

    private FirstChoice firstChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firstChoice = new FirstChoice(getSharedPreferences("appPrefs", Context.MODE_PRIVATE));

        View kwasy = findViewById(R.id.kwasy);
        View alkilo = findViewById(R.id.alkilo);
        View wit_d = findViewById(R.id.wit_d);
        View wit_k = findViewById(R.id.wit_k);

        kwasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstChoice.setFirstChoice("kwasy");
                openKwasy();
            }
        });

        alkilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstChoice.setFirstChoice("alkilo");
                openAlkilo();
            }
        });

        wit_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstChoice.setFirstChoice("wit_d");
                openWitD();
            }
        });
        wit_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstChoice.setFirstChoice("wit_k");
                openWitK();
            }
        });
    }

    @Override
    protected void onNextClicked() {
        firstChoice.setFirstChoice("skład");
        super.onNextClicked();
    }

    private void openKwasy() {
        startActivity(Page8.class);
    }

    private void openAlkilo() {
        startActivity(Page9.class);
    }

    private void openWitD() {
        startActivity(Page10.class);
    }
    private void openWitK() {
        startActivity(Page11.class);
    }

    private void startActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        //intent.putExtra("goToSummary", true);
        startActivity(intent);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page7;
    }

    @Override
    protected Class<?> getNextActivity() {
        return Page8.class;
    }
}
