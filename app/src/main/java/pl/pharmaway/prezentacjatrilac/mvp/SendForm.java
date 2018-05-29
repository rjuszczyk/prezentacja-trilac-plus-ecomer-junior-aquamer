package pl.pharmaway.prezentacjatrilac.mvp;

import pl.pharmaway.prezentacjatrilac.database.NotSendDataRow;

public interface SendForm {
    Cancelable sendForm(NotSendDataRow form, Callback callback);

    interface Callback {
        void onSuccess();
        void onFailure();
    }
}
