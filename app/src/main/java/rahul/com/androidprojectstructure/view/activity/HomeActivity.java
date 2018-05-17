package rahul.com.androidprojectstructure.view.activity;

import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import rahul.com.androidprojectstructure.R;
import rahul.com.androidprojectstructure.adapter.UserAdapter;
import rahul.com.androidprojectstructure.baseclass.BaseActivity;
import rahul.com.androidprojectstructure.databinding.ActivityMainBinding;
import rahul.com.androidprojectstructure.interfaces.ListItemClickListener;
import rahul.com.androidprojectstructure.model.UserModel;
import rahul.com.androidprojectstructure.retrofit.NetworkState;
import rahul.com.androidprojectstructure.viewmodel.HomeVM;

public class HomeActivity extends BaseActivity implements ListItemClickListener {
    private ActivityMainBinding mBinding;
    private HomeVM homeVM;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_main);
    }

    @Override
    public void initVariable() {
        mBinding = (ActivityMainBinding) getBinding();
        homeVM = (HomeVM) setViewModel(HomeVM.class);
    }

    @Override
    public void loadData() {
        setRecyclerView();
        homeVM.initPagination();
        homeVM.getUsers().observe(this, new Observer<PagedList<UserModel>>() {
            @Override
            public void onChanged(@Nullable PagedList<UserModel> userModels) {
                userAdapter.submitList(userModels);
            }
        });

        homeVM.networkState.observe(this, new Observer<NetworkState>() {
            @Override
            public void onChanged(@Nullable NetworkState networkState) {
                userAdapter.setNetworkState(networkState);
            }
        });
    }

    private void setRecyclerView() {
        userAdapter = new UserAdapter(this);
        mBinding.rvUser.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvUser.setAdapter(userAdapter);
    }

    @Override
    public void onRetryClick(View view, int position) {
        homeVM.userDataSource.retryAllFailed();
    }
}
