package rahul.com.androidprojectstructure.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Rahul Sadhu
 */

public class SharedPrefsManager {
    private static final String PREF_NAME = SharedPreferences.class.getPackage().getName();
    private static SharedPrefsManager uniqueInstance;
    private SharedPreferences prefs;

    private SharedPrefsManager(Context appContext) {
        prefs = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    public static SharedPrefsManager getInstance() {
        if (uniqueInstance == null) {
            throw new IllegalStateException(
                    "SharedPrefsManager is not initialized, call initialize(applicationContext) " +
                            "static method first");
        }
        return uniqueInstance;
    }


    public static void initialize(Context appContext) {
        if (appContext == null) {
            throw new NullPointerException("Provided application context is null");
        }
        if (uniqueInstance == null) {
            synchronized (SharedPrefsManager.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new SharedPrefsManager(appContext);
                }
            }
        }
    }

    private SharedPreferences getPrefs() {
        return prefs;
    }

    /**
     * Clears all data in SharedPreferences
     */
    public void clearPrefs() {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.clear();
        editor.apply();
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void removeKey(String key) {
        getPrefs().edit().remove(key).apply();
    }
    public boolean containsKey(String key) {
        return getPrefs().contains(key);
    }

    public String getString(String key) {
        return getPrefs().getString(key, null);
    }


}
