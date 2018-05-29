package pl.pharmaway.prezentacjatrilac;

import android.content.SharedPreferences;

/**
 * Created by Radek on 2018-01-06.
 */

public class FirstChoice {
    private final SharedPreferences sharedPreferences;
    private static final String firstChoice = "firstChoice";

    public FirstChoice(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void reset() {
        sharedPreferences.edit().remove(firstChoice).commit();
    }

    public void setFirstChoice(String choice) {
        if(!sharedPreferences.contains(firstChoice)) {
            sharedPreferences.edit().putString(firstChoice, choice).commit();
        }
    }

    public String getFirstChoice() {
        return sharedPreferences.getString(firstChoice, "unknown");
    }
}
