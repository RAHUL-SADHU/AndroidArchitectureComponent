package rahul.com.androidprojectstructure.view.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import rahul.com.androidprojectstructure.R;
import rahul.com.androidprojectstructure.baseclass.BaseActivity;
import rahul.com.androidprojectstructure.databinding.ActivityLoginBinding;
import rahul.com.androidprojectstructure.model.UserModel;
import rahul.com.androidprojectstructure.utility.Utils;
import rahul.com.androidprojectstructure.viewmodel.LoginVM;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding mBinding;
    private LoginVM loginVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_login);
    }

    @Override
    public void initVariable() {
        loginVM = (LoginVM) setViewModel(LoginVM.class);
        mBinding = (ActivityLoginBinding) getBinding();
    }

    @Override
    public void loadData() {
        loginVM.callKeyApi();
        loginVM.getUserModel().observe(this, new Observer<UserModel>() {
            @Override
            public void onChanged(@Nullable UserModel userModel) {
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startNewActivity(intent);
            }
        });
    }

    public void clickBtnSignIn(View view) {
        if (signInCheckValidation()) {
            loginVM.callSignIn(mBinding.editEmail.getText().toString().trim(),
                    mBinding.editPassword.getText().toString().trim(),
                    Utils.getDeviceId(this));
        }
    }

    public boolean signInCheckValidation() {
        if (TextUtils.isEmpty(mBinding.editEmail.getText().toString().trim())) {
            mBinding.editEmail.setError(getString(R.string.please_enter_email));
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mBinding.editEmail.getText().toString().trim()).matches()) {
            mBinding.editEmail.setError(getString(R.string.please_enter_valid_email));
            return false;
        } else if (TextUtils.isEmpty(mBinding.editPassword.getText().toString().trim())) {
            mBinding.editPassword.setError(getString(R.string.please_enter_password));
            return false;
        } else if (mBinding.editPassword.getText().toString().trim().length() < 5) {
            mBinding.editPassword.setError(getString(R.string.please_enter_minimum_password));
            return false;
        }
        return true;
    }
}
