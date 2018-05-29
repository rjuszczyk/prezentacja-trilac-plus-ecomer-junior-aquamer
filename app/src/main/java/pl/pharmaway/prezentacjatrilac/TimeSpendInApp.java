package pl.pharmaway.prezentacjatrilac;

import android.content.SharedPreferences;

public class TimeSpendInApp {
    private final SharedPreferences sharedPreferences;
    private static final String timeKey = "TIME";
    public TimeSpendInApp(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void reset() {
        sharedPreferences.edit().remove(timeKey).commit();
    }

    public void addTime(long time) {
        long timeTotal = getTime();
        timeTotal += time;
        sharedPreferences.edit().putLong(timeKey, timeTotal).commit();
    }

    long getTime() {
        return sharedPreferences.getLong(timeKey, 0);
    }

    public String getTimeFormatted() {
        long time = getTime();
        return format(time);
    }

    public static String format(long time) {
        long hours = time / (60L*60L*1000L);
        long minutes = time / (60L*1000L);
        long seconds = time / (1000L);
        minutes = minutes % 60L;
        seconds = seconds % 60L;

        if(hours>0) {
            return String.format("%dh %dmin %ds", hours, minutes, seconds);
        } else if(minutes > 0) {
            return String.format("%dmin %ds", minutes, seconds);
        } else {
            return String.format("%ds", seconds);
        }
    }
}
