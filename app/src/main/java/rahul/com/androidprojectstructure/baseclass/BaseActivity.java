package rahul.com.androidprojectstructure.baseclass;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import rahul.com.androidprojectstructure.R;
import rahul.com.androidprojectstructure.interfaces.ActivityImplementMethod;
import rahul.com.androidprojectstructure.utility.Utils;

/**
 * Created by Rahul Sadhu
 */

public abstract class BaseActivity extends AppCompatActivity implements ActivityImplementMethod {
    public BaseViewModel viewModel;
    protected ViewDataBinding binding;
    private Dialog progress_bar_dialog;

    protected void setView(int layoutResId) {
        binding = DataBindingUtil.setContentView(this, layoutResId);
        try {
            initVariable();
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showToast(BaseActivity.this, e.toString());
        }
    }

    public ViewModel setViewModel(Class vModel) {
        viewModel = (BaseViewModel) ViewModelProviders.of(this).get(vModel);
        setObserve();
        return viewModel;
    }

    private void setObserve() {
        viewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean loading) {
                if (loading != null && loading) {
                    showProgressDialog();
                } else {
                    hideProgressDialog();
                }
            }
        });

        viewModel.getApiError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                viewModel.setLoading(false);
                apiError(error);
                Utils.showToast(getApplicationContext(), error);
            }
        });
    }

    public void apiError(String error){

    }


    protected <T extends ViewDataBinding> ViewDataBinding getBinding() {
        return binding;
    }

    public void startNewActivity(Intent intent) {
        //  Utils.hideKeyboard(BaseActivity.this);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Utils.hideKeyboard(BaseActivity.this);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel != null && viewModel.getApiResponse().hasObservers()) {
            viewModel.getApiResponse().removeObserver(viewModel.responseDataObserver);
        }

    }

    public void showProgressDialog() {
        if (progress_bar_dialog == null) {
            progress_bar_dialog = new Dialog(this);
            progress_bar_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progress_bar_dialog.setContentView(R.layout.dialog_progressbar);
            WindowManager.LayoutParams layoutParams = progress_bar_dialog.getWindow().getAttributes();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            progress_bar_dialog.setCancelable(false);
            progress_bar_dialog.setCanceledOnTouchOutside(false);
            progress_bar_dialog.getWindow().setAttributes(layoutParams);
            progress_bar_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }


        if (!progress_bar_dialog.isShowing()) {
            progress_bar_dialog.show();
        }

    }

    public void hideProgressDialog() {
        if (progress_bar_dialog != null && progress_bar_dialog.isShowing()) {
            progress_bar_dialog.dismiss();
        }
    }
}
