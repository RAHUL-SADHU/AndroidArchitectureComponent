package rahul.com.androidprojectstructure.retrofit;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;
import rahul.com.androidprojectstructure.model.AboutUsModel;
import rahul.com.androidprojectstructure.model.PaginationModel;
import rahul.com.androidprojectstructure.model.ResponseData;
import rahul.com.androidprojectstructure.model.RestaurantModel;
import rahul.com.androidprojectstructure.model.UserModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Created by Rahul Sadhu
 */

public interface ApiService {

    String GENERATE_KEY = "key";
    String SIGN_IN = "signin";
    String SIGN_UP = "signup";
    String ABOUT_US = "content/about_us";
    String FRIENDS_PROFILE_DATA = "users";
    String RESTAURANT_LIST = "restaurant/";

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

    @FormUrlEncoded
    @POST(FRIENDS_PROFILE_DATA)
    Call<ResponseData<PaginationModel<UserModel>>> friendsProfileListService(
            @Field("page") int page
    );

    @Multipart
    @POST(RESTAURANT_LIST)
    Call<ResponseData<ArrayList<RestaurantModel>>> restaurantListService(
            @PartMap() Map<String, RequestBody> partMap
    );


}
