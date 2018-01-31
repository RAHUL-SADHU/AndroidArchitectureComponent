package rahul.com.androidprojectstructure.baseclass;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import rahul.com.androidprojectstructure.model.ResponseData;
import rahul.com.androidprojectstructure.retrofit.NetworkManager;

/**
 * Created by Rahul Sadhu
 */

public class BaseViewModel extends ViewModel {

    Observer<ResponseData> responseDataObserver;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private NetworkManager networkManager;

    public BaseViewModel() {
        networkManager = new NetworkManager();
        setObservable();
    }

    private void setObservable() {
        responseDataObserver = new Observer<ResponseData>() {
            @Override
            public void onChanged(@Nullable ResponseData responseData) {
                responseData(responseData);
            }
        };

        getApiResponse().observeForever(responseDataObserver);
    }

    protected NetworkManager getNetworkManager() {
        return networkManager;
    }

    LiveData<Boolean> getLoading() {
        return isLoading;
    }

    protected void setLoading(boolean loading) {
        isLoading.postValue(loading);
    }


    LiveData<ResponseData> getApiResponse() {
        return this.networkManager.apiResponse;
    }


    LiveData<String> getApiError() {
        return this.networkManager.apiError;
    }

    public void responseData(ResponseData responseData) {
        setLoading(false);
    }
}
