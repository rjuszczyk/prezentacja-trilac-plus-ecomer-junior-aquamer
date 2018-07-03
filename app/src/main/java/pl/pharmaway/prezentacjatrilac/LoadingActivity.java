package pl.pharmaway.prezentacjatrilac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import pl.pharmaway.prezentacjatrilac.database.DatabaseHelper;
import pl.pharmaway.prezentacjatrilac.mvp.LoadingPresenter;
import pl.pharmaway.prezentacjatrilac.mvp.LoadingView;
import pl.pharmaway.prezentacjatrilac.mvp.fake.FormDataRepositoryImpl;
import pl.pharmaway.prezentacjatrilac.mvp.fake.LoadingModelImpl;
import pl.pharmaway.prezentacjatrilac.mvp.fake.SendFormImpl;
import pl.pharmaway.prezentacjatrilac.network.PrezentacjaApi;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class LoadingActivity extends FooterActivity implements LoadingView {

    LoadingPresenter loadingPresenter;
    private TextView progressMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressMsg = findViewById(R.id.progressMsg);

        FirstChoice firstChoice = new FirstChoice(getSharedPreferences("appPrefs", Context.MODE_PRIVATE));
        firstChoice.reset();
        TimeSpendInApp timeSpendInApp = new TimeSpendInApp(getSharedPreferences("appPrefs", Context.MODE_PRIVATE));
        timeSpendInApp.reset();

        SharedPreferences sharedPreferences = getSharedPreferences("appPrefs", Context.MODE_PRIVATE);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Gson gson = new Gson();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        PrezentacjaApi prezentacjaApi = retrofit.create(PrezentacjaApi.class);
        loadingPresenter = new LoadingPresenter(
                new LoadingModelImpl(database, prezentacjaApi, sharedPreferences),
                this,
                new SendFormImpl(prezentacjaApi),
                new FormDataRepositoryImpl(this, database)
        );
        loadingPresenter.start();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page0;
    }

    @Override
    protected Class<?> getNextActivity() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadingPresenter.stop();
    }

    @Override
    public void showLoading(String message) {
        progressMsg.setText(message);
    }

    @Override
    public void goToNext() {
        Intent intent = new Intent(this, Page0.class);
        startActivity(intent);
    }
}
