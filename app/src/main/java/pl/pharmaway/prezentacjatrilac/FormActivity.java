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
import pl.pharmaway.prezentacjatrilac.view.ChooseInstytucjaDialog;
import pl.pharmaway.prezentacjatrilac.view.ChooseMiastoDialog;
import pl.pharmaway.prezentacjatrilac.view.ChooseSpecDialog;

public class FormActivity extends AppCompatActivity
        implements ChooseAgentDialog.AgentDialogListener, ChooseSpecDialog.LekarzDialogListener, ChooseMiastoDialog.MiastoDialogListener, ChooseInstytucjaDialog.InstytucjaDialogListener {
    TextView agent;
    TextView spec;
    TextView miasto;
    TextView instytucja;
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
        spec = findViewById(R.id.spec);
        miasto = findViewById(R.id.miasto);
        instytucja = findViewById(R.id.instytucja);
        next = findViewById(R.id.next);

        if(savedInstanceState==null) {
            spec.setEnabled(false);
        } else {
            agent.setText(savedInstanceState.getString("agent"));
            spec.setText(savedInstanceState.getString("spec"));
            miasto.setText(savedInstanceState.getString("miasto"));
            instytucja.setText(savedInstanceState.getString("instytucja"));

            spec.setEnabled(savedInstanceState.getBoolean("spec_enabled"));
            miasto.setEnabled(savedInstanceState.getBoolean("miasto_enabled"));
            instytucja.setEnabled(savedInstanceState.getBoolean("instytucja_enabled"));
        }

        updateNext();

        agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseAgentDialog.create().show(getSupportFragmentManager(), "tag");
            }
        });

        spec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseSpecDialog.create().show(getSupportFragmentManager(), "tag");
            }
        });

        miasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseMiastoDialog.create(agent.getText().toString()).show(getSupportFragmentManager(), "tag");
            }
        });


        instytucja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseInstytucjaDialog.create(agent.getText().toString(), miasto.getText().toString()).show(getSupportFragmentManager(), "tag");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstChoice firstChoice = new FirstChoice(getSharedPreferences("appPrefs", Context.MODE_PRIVATE));
                TimeSpendInApp timeSpendInApp = new TimeSpendInApp(getSharedPreferences("appPrefs", Context.MODE_PRIVATE));

                NotSendDataRow notSendDataRow = new NotSendDataRow();

                notSendDataRow.pm = agent.getText().toString();
                notSendDataRow.spec = spec.getText().toString();
                notSendDataRow.m = miasto.getText().toString();
                notSendDataRow.i = instytucja.getText().toString();
                notSendDataRow.appId = Constants.APP_ID;
                notSendDataRow.createDate = new Date().toString();
                notSendDataRow.timeInApp = timeSpendInApp.getTimeFormatted();
                notSendDataRow.firstChoice = firstChoice.getFirstChoice();

                Log.d("TIME IN APP", notSendDataRow.timeInApp);

                repository.storeNotSendForm(notSendDataRow);

                Intent intent = new Intent(FormActivity.this, LoadingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onAgentSelected(String agent) {
        this.agent.setText(agent);
        this.spec.setEnabled(true);
        this.spec.setText("");
        this.miasto.setText("");
        this.instytucja.setText("");
        updateNext();
    }

    @Override
    public void onSpecSelected(String lekarz) {
        this.spec.setText(lekarz);
        this.miasto.setEnabled(true);
        this.miasto.setText("");
        this.instytucja.setText("");
        updateNext();
    }

    @Override
    public void onMiastoSelected(String miasto) {
        this.miasto.setText(miasto);
        this.instytucja.setEnabled(true);
        this.instytucja.setText("");
        updateNext();
    }

    @Override
    public void onInstytucjaSelected(String instytucja) {
        this.instytucja.setText(instytucja);
        updateNext();
    }

    public String getAgentText() {
        return agent.getText().toString();
    }

    void updateNext() {
        next.setEnabled(!TextUtils.isEmpty(this.instytucja.getText()));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("agent", agent.getText().toString());
        outState.putString("spec", spec.getText().toString());
        outState.putString("miasto", miasto.getText().toString());
        outState.putString("instytucja", instytucja.getText().toString());
        outState.putBoolean("spec_enabled", spec.isEnabled());
        outState.putBoolean("miasto_enabled", miasto.isEnabled());
        outState.putBoolean("instytucja_enabled", instytucja.isEnabled());
    }


}
