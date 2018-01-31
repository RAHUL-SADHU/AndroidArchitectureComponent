package rahul.com.androidprojectstructure.retrofit;

import android.arch.lifecycle.MutableLiveData;

import rahul.com.androidprojectstructure.baseclass.SingleLiveData;
import rahul.com.androidprojectstructure.model.AboutUsModel;
import rahul.com.androidprojectstructure.model.ResponseData;
import rahul.com.androidprojectstructure.model.UserModel;
import retrofit2.Call;

/**
 * Created by Rahul Sadhu
 */

public class NetworkManager {
    public SingleLiveData<String> apiError = new SingleLiveData<>();
    public MutableLiveData<ResponseData> apiResponse = new MutableLiveData<>();


    public void RequestStringData(Call<ResponseData<String>> call, final String key) {
        call.enqueue(new CallbackResponse<ResponseData<String>>() {
            @Override
            public void onSuccessResponse(Call<ResponseData<String>> call, ResponseData<ResponseData<String>> response) {
                response.key = key;
                apiResponse.postValue(response);
            }

            @Override
            public void onFailureResponse(Call<ResponseData<String>> call, ResponseData<ResponseData<String>> responseData) {
                apiError.postValue(responseData.message);
            }
        });
    }


    public void RequestUserData(Call<ResponseData<UserModel>> call, final String key) {
        call.enqueue(new CallbackResponse<ResponseData<UserModel>>() {
            @Override
            public void onSuccessResponse(Call<ResponseData<UserModel>> call, ResponseData<ResponseData<UserModel>> response) {
                response.key = key;
                apiResponse.postValue(response);
            }

            @Override
            public void onFailureResponse(Call<ResponseData<UserModel>> call, ResponseData<ResponseData<UserModel>> responseData) {
                apiError.postValue(responseData.message);
            }
        });
    }

    public void RequestAboutUsData(Call<ResponseData<AboutUsModel>> call, final String key) {
        call.enqueue(new CallbackResponse<ResponseData<AboutUsModel>>() {
            @Override
            public void onSuccessResponse(Call<ResponseData<AboutUsModel>> call, ResponseData<ResponseData<AboutUsModel>> response) {
                response.key = key;
                apiResponse.postValue(response);
            }

            @Override
            public void onFailureResponse(Call<ResponseData<AboutUsModel>> call, ResponseData<ResponseData<AboutUsModel>> responseData) {
                apiError.postValue(responseData.message);
            }
        });
    }
}
