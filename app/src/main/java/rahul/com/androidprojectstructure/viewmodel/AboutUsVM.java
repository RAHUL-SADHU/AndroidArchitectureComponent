package rahul.com.androidprojectstructure.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import rahul.com.androidprojectstructure.baseclass.BaseViewModel;
import rahul.com.androidprojectstructure.model.AboutUsModel;
import rahul.com.androidprojectstructure.model.ResponseData;
import rahul.com.androidprojectstructure.retrofit.ApiService;
import rahul.com.androidprojectstructure.retrofit.RetrofitClient;
import retrofit2.Call;

/**
 * Created by Rahul Sadhu
 */

public class AboutUsVM extends BaseViewModel{

    private MutableLiveData<AboutUsModel> aboutUsModel = new MutableLiveData<>();
    
    public void callAboutUs(){
        setLoading(true);
        Call<ResponseData<AboutUsModel>> call = RetrofitClient.getInstance().getApiInterface().aboutUsService();
        getNetworkManager().RequestAboutUsData(call, ApiService.ABOUT_US);
    }

    @Override
    public void responseData(ResponseData responseData) {
        super.responseData(responseData);
        aboutUsModel.setValue((AboutUsModel) responseData.data);
    }

    public LiveData<AboutUsModel> getAboutUsModel(){
        return this.aboutUsModel;
    }

}
