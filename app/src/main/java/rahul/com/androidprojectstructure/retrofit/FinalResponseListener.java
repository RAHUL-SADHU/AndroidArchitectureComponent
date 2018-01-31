package rahul.com.androidprojectstructure.retrofit;


import rahul.com.androidprojectstructure.model.ResponseData;
import retrofit2.Call;

/**
 * Created by Rahul Sadhu
 */
public interface FinalResponseListener<T> {
    void onSuccessResponse(Call<T> call, ResponseData<T> response);

    void onFailureResponse(Call<T> call, ResponseData<T> responseData);
}
