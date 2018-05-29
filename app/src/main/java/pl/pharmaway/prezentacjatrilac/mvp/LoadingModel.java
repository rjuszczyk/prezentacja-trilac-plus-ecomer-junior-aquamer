package pl.pharmaway.prezentacjatrilac.mvp;

public interface LoadingModel {
    Cancelable checkUpdate(CheckUpdateCallback checkUpdateCallback);

    interface CheckUpdateCallback {
        void onLoaded(boolean needUpdate);
        void onFailed();
    }

    Cancelable downloadDatabase(DownloadDatabaseCallback downloadDatabaseCallback);

    interface DownloadDatabaseCallback {
        void onProgress(double progress);
        void onFailed();
        void onDownloaded();
    }
}
