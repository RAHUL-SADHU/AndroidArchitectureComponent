package rahul.com.androidprojectstructure.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import rahul.com.androidprojectstructure.baseclass.BaseViewModel;
import rahul.com.androidprojectstructure.model.ResponseData;
import rahul.com.androidprojectstructure.model.UserModel;
import rahul.com.androidprojectstructure.retrofit.ApiService;
import rahul.com.androidprojectstructure.retrofit.RetrofitClient;
import rahul.com.androidprojectstructure.utility.GlobalKeys;
import rahul.com.androidprojectstructure.utility.SharedPrefsManager;
import retrofit2.Call;

/**
 * Created by Rahul Sadhu
 */

public class LoginVM extends BaseViewModel {

    private MutableLiveData<UserModel> userModelLiveData = new MutableLiveData<>();

    public void callKeyApi() {
        Call<ResponseData<String>> call = RetrofitClient.getInstance().getApiInterface().getKey();
        getNetworkManager().RequestStringData(call, ApiService.GENERATE_KEY);
    }


    public void callSignIn(String email, String password, String deviceId) {
        setLoading(true);
        Call<ResponseData<UserModel>> call = RetrofitClient.getInstance().getApiInterface().singInService(email,password,deviceId);
        getNetworkManager().RequestUserData(call, ApiService.SIGN_IN);
    }

    public LiveData<UserModel> getUserModel(){
        return this.userModelLiveData;
    }

    @Override
    public void responseData(ResponseData responseData) {
        super.responseData(responseData);
        if (responseData.key.equals(ApiService.GENERATE_KEY)) {
            SharedPrefsManager.getInstance().setString(GlobalKeys.KEY_X_API, (String) responseData.data);
        } else if (responseData.key.equals(ApiService.SIGN_IN)) {
            userModelLiveData.setValue((UserModel) responseData.data);
        }
    }
}
