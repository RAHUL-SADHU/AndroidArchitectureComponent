package rahul.com.androidprojectstructure.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import rahul.com.androidprojectstructure.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rahul Sadhu
 */

public class RetrofitClient {
    private static RetrofitClient retrofitClient = null;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static HeaderInterceptor headerInterceptor;
    private ApiService apiService;

    private RetrofitClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        httpClient.connectTimeout(1, TimeUnit.MINUTES);
        httpClient.readTimeout(1, TimeUnit.MINUTES);
        httpClient.retryOnConnectionFailure(true);
        headerInterceptor = new HeaderInterceptor();
        addInterceptors();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    public static RetrofitClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public ApiService getApiInterface() {
        return apiService;
    }



    private static void addInterceptors() {

        if (!httpClient.interceptors().contains(headerInterceptor)) {
            httpClient.addInterceptor(headerInterceptor);
        }

        if (BuildConfig.DEBUG && !httpClient.networkInterceptors().contains(loggingInterceptor)) {
            httpClient.addNetworkInterceptor(loggingInterceptor);
        }
    }
}
