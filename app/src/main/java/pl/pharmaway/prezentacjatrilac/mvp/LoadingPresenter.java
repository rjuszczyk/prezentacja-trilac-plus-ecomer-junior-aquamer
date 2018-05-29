package pl.pharmaway.prezentacjatrilac.mvp;

public class LoadingPresenter {
    private final LoadingModel loadingModel;
    private final LoadingView loadingView;
    private final FormDataRepository formDataRepository;
    private final FormSendingManager formSendingManager;
    private Cancelable cancelable;

    public LoadingPresenter(LoadingModel loadingModel, LoadingView loadingView, SendForm sendForm, FormDataRepository formDataRepository) {
        this.loadingModel = loadingModel;
        this.loadingView = loadingView;
        this.formDataRepository = formDataRepository;
        formSendingManager = new FormSendingManager(formDataRepository, sendForm);
    }

    public void start() {
        loadingView.showLoading("Sprawdzam aktualizacje");
        cancelable = loadingModel.checkUpdate(new LoadingModel.CheckUpdateCallback() {
            @Override
            public void onLoaded(boolean needUpdate) {
                if (needUpdate) {
                    loadingView.showLoading("Aktualizuje baze");
                    cancelable = loadingModel.downloadDatabase(new LoadingModel.DownloadDatabaseCallback() {
                        @Override
                        public void onProgress(double progress) {
                            loadingView.showLoading("Pobieram baze (" + progress + ")");
                        }

                        @Override
                        public void onFailed() {
                            loadingView.goToNext();
                        }

                        @Override
                        public void onDownloaded() {
                            sendNotSended();
                        }
                    });
                } else {
                    sendNotSended();
                }
            }

            @Override
            public void onFailed() {
                loadingView.goToNext();
            }
        });
    }

    private void sendNotSended() {
        if (!formDataRepository.hasNotSendForms()) {
            loadingView.goToNext();
        } else {
            loadingView.showLoading("Wysyłam zaległe ankiety");
            cancelable = formSendingManager.performSending(new FormSendingManager.SendingCallback() {
                @Override
                public void onProgress(int left) {
                    loadingView.showLoading("Wysyłam zaległe ankiety (zostało " + left + ")");
                }

                @Override
                public void onSuccess() {
                    loadingView.showLoading("Wysłano zaległe ankiety");
                    loadingView.goToNext();
                }

                @Override
                public void onFailed() {
                    loadingView.showLoading("Błąd wysyłania");
                    loadingView.goToNext();
                }
            });
        }
    }

    public void stop() {
        cancelable.cancel();
    }
}
