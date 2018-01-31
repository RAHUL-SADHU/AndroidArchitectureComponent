package rahul.com.androidprojectstructure.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import rahul.com.androidprojectstructure.R;
import rahul.com.androidprojectstructure.baseclass.BaseActivity;
import rahul.com.androidprojectstructure.utility.UtilConstants;
import rahul.com.androidprojectstructure.utility.GlobalKeys;
import rahul.com.androidprojectstructure.utility.SharedPrefsManager;

public class SplashActivity extends BaseActivity {
    private Handler myHandler;
    private Runnable myRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_splash);
    }

    @Override
    public void initVariable() {
        myHandler = new Handler();
    }

    @Override
    public void loadData() {
        if (!SharedPrefsManager.getInstance().containsKey(GlobalKeys.KEY_X_API)) {
            SharedPrefsManager.getInstance().setString(GlobalKeys.KEY_X_API, UtilConstants.X_API_KEY);
        }

        myHandler.postDelayed(myRunnable = new Runnable() {
            @Override
            public void run() {
                if (SharedPrefsManager.getInstance().containsKey(GlobalKeys.KEY_USER_ID)) {
                    startHomeActivity();
                } else {
                    startLoginActivity();
                }

            }
        }, 2000);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startNewActivity(intent);
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startNewActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myHandler != null) {
            myHandler.removeCallbacks(myRunnable);
        }
    }


}
