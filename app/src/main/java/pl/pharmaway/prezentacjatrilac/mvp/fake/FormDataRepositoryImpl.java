package pl.pharmaway.prezentacjatrilac.mvp.fake;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import pl.pharmaway.prezentacjatrilac.database.DatabaseHelper;
import pl.pharmaway.prezentacjatrilac.database.NotSendDataRow;
import pl.pharmaway.prezentacjatrilac.mvp.FormDataRepository;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class FormDataRepositoryImpl implements FormDataRepository {

    private final Context context;
    private final SQLiteDatabase database;

    public FormDataRepositoryImpl(Context context, SQLiteDatabase database) {
        this.context = context;
        this.database = database;
    }

    @Override
    public List<NotSendDataRow> getNotSendForms() {
        return DatabaseHelper.getNotSendDataRows(context);
    }

    @Override
    public void markAsSend(NotSendDataRow form) {
        boolean wasDeleted = cupboard().withDatabase(database)
                .delete(form);
        if(!wasDeleted) throw new RuntimeException();
    }

    @Override
    public boolean hasNotSendForms() {
        return DatabaseHelper.getNotSendCount(context) > 0;
    }

    @Override
    public void storeNotSendForm(NotSendDataRow notSendDataRow) {
        cupboard().withDatabase(database).put(notSendDataRow);
    }
}
