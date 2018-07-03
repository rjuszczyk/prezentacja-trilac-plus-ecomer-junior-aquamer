package pl.pharmaway.prezentacjatrilac.mvp;

import java.util.ArrayList;
import java.util.List;

import pl.pharmaway.prezentacjatrilac.database.NotSendDataRow;

public class FormSendingManager {
    private final FormDataRepository formDataRepository;
    private final SendForm sendForm;

    public FormSendingManager(FormDataRepository formDataRepository, SendForm sendForm) {
        this.formDataRepository = formDataRepository;
        this.sendForm = sendForm;
    }

    public Cancelable performSending(SendingCallback sendingCallback) {
        SendingCancleable cancleable = new SendingCancleable();
        List<NotSendDataRow> formList = formDataRepository.getNotSendForms();
        sendNext(cancleable, formList, sendingCallback);

        return cancleable;
    }

    private void sendNext(final SendingCancleable cancleable, final List<NotSendDataRow> formList, final SendingCallback sendingCallback) {
        sendingCallback.onProgress(formList.size());
        cancleable.addCancelable(sendForm.sendForm(formList.get(0), new SendForm.Callback() {
            @Override
            public void onSuccess() {
                NotSendDataRow form = formList.remove(0);
                formDataRepository.markAsSend(form);
                if(formList.isEmpty()) {
                    sendingCallback.onSuccess();
                } else {
                    sendNext(cancleable, formList, sendingCallback);
                }
            }

            @Override
            public void onFailure() {
                sendingCallback.onFailed();
            }
        }));
    }

    class SendingCancleable implements Cancelable {
        List<Cancelable> subCancelables = new ArrayList<>();
        boolean isCanceled = false;

        public void addCancelable(Cancelable cancelable) {
            subCancelables.add(cancelable);
        }

        @Override
        public boolean isCanceled() {
            return isCanceled;
        }

        @Override
        public void cancel() {
            isCanceled = true;
            for (Cancelable subCancelable : subCancelables) {
                subCancelable.cancel();
            }
        }
    }

    interface SendingCallback {
        void onProgress(int left);
        void onSuccess();
        void onFailed();
    }
}
