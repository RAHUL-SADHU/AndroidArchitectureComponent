package rahul.com.androidprojectstructure.baseclass;

import android.app.Application;

import rahul.com.androidprojectstructure.utility.SharedPrefsManager;

/**
 * Created by Rahul Sadhu
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefsManager.initialize(this);
    }
}
