package pl.pharmaway.prezentacjatrilac.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database2.db";
    public static final int VERSION = 1;

    static {
        CupboardFactory.setCupboard(new CupboardBuilder().useAnnotations().build());

        cupboard().register(DataRow.class);
        cupboard().register(NotSendDataRow.class);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        cupboard()
                .withDatabase(sqLiteDatabase)
                .createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        cupboard()
                .withDatabase(sqLiteDatabase)
                .upgradeTables();
    }

    public static List<DataRow> rowsForLekarzType(
            Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return cupboard()
                .withDatabase(db)
                .query(DataRow.class)
                .groupBy("pm")
                .list();
    }

    public static List<DataRow> rowsForLekarzTypeAndAgent(
            String pm,
            Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return cupboard()
                .withDatabase(db)
                .query(DataRow.class)
                .withSelection(
                        "pm LIKE ?",
                        new String[]{pm})
                .groupBy("lekarz")
                .list();
    }

    public static List<NotSendDataRow> getNotSendDataRows(
            Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return cupboard()
                .withDatabase(db)
                .query(NotSendDataRow.class)
                .list();
    }

    public static int getNotSendCount(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = cupboard()
                .withDatabase(db)
                .query(NotSendDataRow.class)
                .getCursor();
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public static List<DataRow> miastoForPm(Context context, String pm) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return cupboard()
                .withDatabase(db)
                .query(DataRow.class)
                .withSelection(
                        "pm LIKE ?",
                        new String[]{pm})
                .groupBy("m")
                .list();
    }

    public static List<DataRow> instytucjaForPmAndM(Context context, String pm, String m) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return cupboard()
                .withDatabase(db)
                .query(DataRow.class)
                .withSelection(
                        "pm LIKE ? and m LIKE ?",
                        new String[]{pm, m})
                .groupBy("m")
                .list();
    }
}