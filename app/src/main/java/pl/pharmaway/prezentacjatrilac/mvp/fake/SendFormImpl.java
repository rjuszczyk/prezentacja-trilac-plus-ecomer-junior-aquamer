package pl.pharmaway.prezentacjatrilac.mvp.fake;

import android.annotation.SuppressLint;
import android.os.Handler;

import pl.pharmaway.prezentacjatrilac.database.NotSendDataRow;
import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;
import pl.pharmaway.prezentacjatrilac.mvp.Form;
import pl.pharmaway.prezentacjatrilac.mvp.SendForm;
import pl.pharmaway.prezentacjatrilac.network.DataVersion;
import pl.pharmaway.prezentacjatrilac.network.PrezentacjaApi;
import pl.pharmaway.prezentacjatrilac.network.SendResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class SendFormImpl implements SendForm {
    private final PrezentacjaApi prezentacjaApi;
    public SendFormImpl(PrezentacjaApi prezentacjaApi) {
        this.prezentacjaApi = prezentacjaApi;
    }

    @Override
    public Cancelable sendForm(NotSendDataRow form, final Callback callback) {

        final Call<SendResponse> call = prezentacjaApi.send(
                form.createDate,
                form.pm,
                form.spec,
                form.m,
                form.i,
                form.timeInApp,
                form.firstChoice
        );

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
        };

        call.enqueue(new retrofit.Callback<SendResponse>() {

            @SuppressLint("ApplySharedPref")
            @Override
            public void onResponse(Response<SendResponse> response) {
                if (!cancelable.isCanceled()) {
                    if(response.body()==null){
                        onFailure(null);
                        return;
                    }

                    if(response.body().isSuccess()) {
                        callback.onSuccess();
                    } else {
                        onFailure(null);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure();
            }
        });

        return cancelable;
    }
}
