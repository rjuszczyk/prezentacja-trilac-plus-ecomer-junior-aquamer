package pl.pharmaway.prezentacjatrilac.mvp;

public interface Cancelable {
    boolean isCanceled();
    void cancel();
}
