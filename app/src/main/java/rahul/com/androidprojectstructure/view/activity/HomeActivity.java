package rahul.com.androidprojectstructure.view.activity;

import android.os.Bundle;

import rahul.com.androidprojectstructure.R;
import rahul.com.androidprojectstructure.baseclass.BaseActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_main);
    }

    @Override
    public void initVariable() {

    }

    @Override
    public void loadData() {

    }
}
