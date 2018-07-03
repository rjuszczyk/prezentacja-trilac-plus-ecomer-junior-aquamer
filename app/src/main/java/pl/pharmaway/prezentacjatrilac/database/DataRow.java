package pl.pharmaway.prezentacjatrilac.database;


public class DataRow {
    public String pm;
    public String m;
    public String i;

    public static DataRow create(String spec) {
        DataRow dataRow = new DataRow();
        dataRow.pm = spec;
        return dataRow;
    }
}
