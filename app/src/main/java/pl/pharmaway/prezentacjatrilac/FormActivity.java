package pl.pharmaway.prezentacjatrilac;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import pl.pharmaway.prezentacjatrilac.database.DatabaseHelper;
import pl.pharmaway.prezentacjatrilac.database.NotSendDataRow;
import pl.pharmaway.prezentacjatrilac.mvp.fake.FormDataRepositoryImpl;
import pl.pharmaway.prezentacjatrilac.view.ChooseAgentDialog;
import pl.pharmaway.prezentacjatrilac.view.ChooseLekarzDialog;

public class FormActivity extends AppCompatActivity
implements ChooseAgentDialog.AgentDialogListener, ChooseLekarzDialog.LekarzDialogListener {
    TextView agent;
    TextView lekarz;
    View next;
    private FormDataRepositoryImpl repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        repository = new FormDataRepositoryImpl(this, database);

        agent = findViewById(R.id.agent);
        lekarz = findViewById(R.id.lekarz);
        next = findViewById(R.id.next);

        if(savedInstanceState==null) {
            lekarz.setEnabled(false);
        } else {
            agent.setText(savedInstanceState.getString("agent"));
            lekarz.setText(savedInstanceState.getString("lekarz"));
            lekarz.setEnabled(savedInstanceState.getBoolean("lekarz_enabled"));
        }

        updateNext();

        agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseAgentDialog.create().show(getSupportFragmentManager(), "tag");
            }
        });

        lekarz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseLekarzDialog.create(getAgentText()).show(getSupportFragmentManager(), "tag");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstChoice firstChoice = new FirstChoice(getSharedPreferences("appPrefs", Context.MODE_PRIVATE));
                TimeSpendInApp timeSpendInApp = new TimeSpendInApp(getSharedPreferences("appPrefs", Context.MODE_PRIVATE));

                NotSendDataRow notSendDataRow = new NotSendDataRow();

                notSendDataRow.agent = agent.getText().toString();
                notSendDataRow.lekarz = lekarz.getText().toString();
                notSendDataRow.appId = Constants.APP_ID;
                notSendDataRow.createDate = new Date().toString();
                notSendDataRow.lekarzType = Constants.LEKARZ_TYPE;
                notSendDataRow.timeInApp = timeSpendInApp.getTimeFormatted();
                notSendDataRow.firstChoice = firstChoice.getFirstChoice();

                Log.d("TIME IN APP", notSendDataRow.timeInApp);

                repository.storeNotSendForm(notSendDataRow);

                Intent intent = new Intent(FormActivity.this, PageSummary.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onAgentSelected(String agent) {
        this.agent.setText(agent);
        this.lekarz.setEnabled(true);
        this.lekarz.setText("");
        updateNext();
    }

    @Override
    public void onLekarzSelected(String lekarz) {
        this.lekarz.setText(lekarz);
        updateNext();
    }

    public String getAgentText() {
        return agent.getText().toString();
    }

    void updateNext() {
        next.setEnabled(!TextUtils.isEmpty(this.lekarz.getText()));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("agent", agent.getText().toString());
        outState.putString("lekarz", lekarz.getText().toString());
        outState.putBoolean("lekarz_enabled", lekarz.isEnabled());
    }
}
