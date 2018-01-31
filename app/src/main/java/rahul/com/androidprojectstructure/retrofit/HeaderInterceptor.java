package rahul.com.androidprojectstructure.retrofit;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import rahul.com.androidprojectstructure.utility.GlobalKeys;
import rahul.com.androidprojectstructure.utility.SharedPrefsManager;

/**
 * Created by Rahul Sadhu
 */

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .method(original.method(), original.body());
        requestBuilder.addHeader(GlobalKeys.KEY_X_API, SharedPrefsManager.getInstance().getString(GlobalKeys.KEY_X_API));
        if (SharedPrefsManager.getInstance().containsKey(GlobalKeys.KEY_ACCESS_TOKEN)) {
            requestBuilder.addHeader(GlobalKeys.KEY_ACCESS_TOKEN, SharedPrefsManager.getInstance().getString(GlobalKeys.KEY_ACCESS_TOKEN));
        }
        if (SharedPrefsManager.getInstance().containsKey(GlobalKeys.KEY_USER_ID)) {
            requestBuilder.addHeader(GlobalKeys.KEY_USER_ID, SharedPrefsManager.getInstance().getString(GlobalKeys.KEY_USER_ID));
        }
        return chain.proceed(requestBuilder.build());
    }
}
