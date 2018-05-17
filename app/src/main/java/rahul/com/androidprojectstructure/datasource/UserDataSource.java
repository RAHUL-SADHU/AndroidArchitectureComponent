package rahul.com.androidprojectstructure.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import rahul.com.androidprojectstructure.model.PaginationModel;
import rahul.com.androidprojectstructure.model.ResponseData;
import rahul.com.androidprojectstructure.model.UserModel;
import rahul.com.androidprojectstructure.retrofit.NetworkState;
import rahul.com.androidprojectstructure.retrofit.RetrofitClient;
import rahul.com.androidprojectstructure.retrofit.Status;
import rahul.com.androidprojectstructure.utility.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 15/5/18.
 */
public class UserDataSource extends PageKeyedDataSource<Integer, UserModel> {

    private Executor retryExecutor;
    private MutableLiveData<NetworkState> networkState = new MutableLiveData<NetworkState>();
    private Runnable retry;

    public UserDataSource(Executor executor) {
        this.retryExecutor = executor;
    }

    public MutableLiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public void retryAllFailed() {
        retryExecutor.execute(retry);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, UserModel> callback) {
        Utils.Log("LoadInitial ");
        networkState.postValue(NetworkState.LOADING);
        Call<ResponseData<PaginationModel<UserModel>>> call = RetrofitClient.getInstance().getApiInterface().friendsProfileListService(1);
        call.enqueue(new Callback<ResponseData<PaginationModel<UserModel>>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseData<PaginationModel<UserModel>>> call, @NonNull Response<ResponseData<PaginationModel<UserModel>>> response) {
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    callback.onResult(response.body().data.data, 1, 2);
                    networkState.postValue(NetworkState.LOADED);
                } else {
                    networkState.postValue(new NetworkState(Status.FAILED, response.message()));

                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseData<PaginationModel<UserModel>>> call, @NonNull Throwable t) {
                String errorMessage;
                errorMessage = t.getMessage();
                networkState.postValue(new NetworkState(Status.FAILED, errorMessage));
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModel> callback) {
        Utils.Log("LoadBefore " + params.key);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModel> callback) {

        Utils.Log("LoadAfter" + params.key);

        networkState.postValue(NetworkState.LOADING);
        Call<ResponseData<PaginationModel<UserModel>>> call = RetrofitClient.getInstance().getApiInterface().friendsProfileListService(params.key);
        call.enqueue(new Callback<ResponseData<PaginationModel<UserModel>>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseData<PaginationModel<UserModel>>> call, @NonNull Response<ResponseData<PaginationModel<UserModel>>> response) {
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    callback.onResult(response.body().data.data, params.key + 1);
                    networkState.postValue(NetworkState.LOADED);
                } else {
                    networkState.postValue(new NetworkState(Status.FAILED, response.message()));
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseData<PaginationModel<UserModel>>> call, Throwable t) {
                String errorMessage;
                errorMessage = t.getMessage();
                networkState.postValue(new NetworkState(Status.FAILED, errorMessage));
                retry = new Runnable() {
                    @Override
                    public void run() {
                        loadAfter(params, callback);
                    }
                };
            }
        });
    }
}
