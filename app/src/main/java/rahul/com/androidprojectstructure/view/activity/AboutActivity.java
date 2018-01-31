package rahul.com.androidprojectstructure.view.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;

import rahul.com.androidprojectstructure.R;
import rahul.com.androidprojectstructure.baseclass.BaseActivity;
import rahul.com.androidprojectstructure.databinding.ActivityAboutBinding;
import rahul.com.androidprojectstructure.model.AboutUsModel;
import rahul.com.androidprojectstructure.viewmodel.AboutUsVM;

public class AboutActivity extends BaseActivity {

    ActivityAboutBinding mBinding;
    AboutUsVM aboutUsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_about);
    }

    @Override
    public void initVariable() {
        aboutUsVM = (AboutUsVM) setViewModel(AboutUsVM.class);
        mBinding = (ActivityAboutBinding) getBinding();
    }

    @Override
    public void loadData() {
        aboutUsVM.callAboutUs();
        aboutUsVM.getAboutUsModel().observe(this, new Observer<AboutUsModel>() {
            @Override
            public void onChanged(@Nullable AboutUsModel aboutUsModel) {
                assert aboutUsModel != null;
                mBinding.txtContent.setText(Html.fromHtml(aboutUsModel.getContent()));
            }
        });
    }

    @Override
    public void apiError(String error) {
        super.apiError(error);
        mBinding.txtContent.setText(error);
    }
}
