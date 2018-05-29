package pl.pharmaway.prezentacjatrilac.mvp;

import java.util.List;

import pl.pharmaway.prezentacjatrilac.database.NotSendDataRow;

public interface FormDataRepository {
    List<NotSendDataRow> getNotSendForms();
    void markAsSend(NotSendDataRow form);
    boolean hasNotSendForms();
    void storeNotSendForm(NotSendDataRow form);
}
