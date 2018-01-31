package rahul.com.androidprojectstructure.baseclass;

import android.app.Dialog;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import rahul.com.androidprojectstructure.R;
import rahul.com.androidprojectstructure.interfaces.ActivityImplementMethod;
import rahul.com.androidprojectstructure.utility.Utils;

/**
 * Created by Rahul Sadhu
 */

public class BaseFragment extends Fragment implements ActivityImplementMethod{
    protected int layoutId;
    protected ViewDataBinding binding;
    private View view;
    private Dialog progress_bar_dialog;

    protected void setView(int layoutId) {
        this.layoutId = layoutId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        view = binding.getRoot();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            initVariable();
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showToast(getActivity(), e.toString());
        }

    }

    protected <T extends ViewDataBinding> T getBinding() {
        return (T) binding;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public void loadData() {

    }

    public void showProgressDialog() {
        if (progress_bar_dialog == null) {
            progress_bar_dialog = new Dialog(getActivity());
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
