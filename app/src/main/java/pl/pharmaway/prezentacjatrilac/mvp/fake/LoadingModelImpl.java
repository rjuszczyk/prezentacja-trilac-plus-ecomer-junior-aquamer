package pl.pharmaway.prezentacjatrilac.mvp.fake;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import pl.pharmaway.prezentacjatrilac.database.DataRow;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;
import pl.pharmaway.prezentacjatrilac.mvp.LoadingModel;
import pl.pharmaway.prezentacjatrilac.network.DataVersion;
import pl.pharmaway.prezentacjatrilac.network.GetDataResponse;
import pl.pharmaway.prezentacjatrilac.network.PrezentacjaApi;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class LoadingModelImpl implements LoadingModel {

    private final SQLiteDatabase database;
    private final PrezentacjaApi prezentacjaApi;
    private final SharedPreferences sharedPreferences;

    public LoadingModelImpl(
            SQLiteDatabase database,
            PrezentacjaApi prezentacjaApi,
            SharedPreferences sharedPreferences
    ) {
        this.database = database;
        this.prezentacjaApi = prezentacjaApi;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Cancelable checkUpdate(final CheckUpdateCallback checkUpdateCallback) {
        final Call<DataVersion> call = prezentacjaApi.getDataVersion();
        final Cancelable cancelable = new Cancelable() {
            boolean isCanceled = false;
            @Override
            public boolean isCanceled() {
                return isCanceled;
            }

            @Override
            public void cancel() {
                isCanceled = true;
                call.cancel();
            }

            @Override
            public int getAnimationLength() {
                return 0;
            }
        };

        call.enqueue(new Callback<DataVersion>() {

            @SuppressLint("ApplySharedPref")
            @Override
            public void onResponse(Response<DataVersion> response) {
                if (!cancelable.isCanceled()) {
                    int version = sharedPreferences.getInt("version", 0);
                    int newVersion = response.body().getVersion();
                    sharedPreferences.edit().putInt("version", newVersion).commit();
                    checkUpdateCallback.onLoaded(newVersion >version);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                checkUpdateCallback.onFailed();
            }
        });

        return cancelable;
    }

    @Override
    public Cancelable downloadDatabase(final DownloadDatabaseCallback downloadDatabaseCallback) {
        final Call<GetDataResponse> call = prezentacjaApi.getData();
        final Cancelable cancelable = new Cancelable() {
            boolean isCanceled = false;
            @Override
            public boolean isCanceled() {
                return isCanceled;
            }

            @Override
            public void cancel() {
                isCanceled = true;
                call.cancel();
            }

            @Override
            public int getAnimationLength() {
                return 0;
            }
        };

        call.enqueue(new Callback<GetDataResponse>() {
            @Override
            public void onResponse(Response<GetDataResponse> response) {
                database.beginTransaction();
                List<DataRow> pharmacyDataList = response.body().getDataList();
                cupboard().withDatabase(database).delete(DataRow.class, "1");
                cupboard().withDatabase(database).put(pharmacyDataList);
                if (!cancelable.isCanceled()) {
                    database.setTransactionSuccessful();
                }
                database.endTransaction();
                if (!cancelable.isCanceled()) {
                    downloadDatabaseCallback.onDownloaded();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                downloadDatabaseCallback.onFailed();
            }
        });

        return cancelable;
    }
}
