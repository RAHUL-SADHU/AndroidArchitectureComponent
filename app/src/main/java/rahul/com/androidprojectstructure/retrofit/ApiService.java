package rahul.com.androidprojectstructure.retrofit;

import rahul.com.androidprojectstructure.model.AboutUsModel;
import rahul.com.androidprojectstructure.model.ResponseData;
import rahul.com.androidprojectstructure.model.UserModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Rahul Sadhu
 */

public interface ApiService {

    String GENERATE_KEY = "key/index";
    String SIGN_IN = "signin";
    String SIGN_UP = "signup";
    String ABOUT_US = "content/about_us";

    @POST(GENERATE_KEY)
    Call<ResponseData<String>> getKey();

    @FormUrlEncoded
    @POST(SIGN_IN)
    Call<ResponseData<UserModel>> singInService(
            @Field("email") String email,
            @Field("password") String password,
            @Field("device_token") String deviceToken
    );

    @POST(ABOUT_US)
    Call<ResponseData<AboutUsModel>> aboutUsService();


}
