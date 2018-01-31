package rahul.com.androidprojectstructure.retrofit;

import android.support.annotation.NonNull;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rahul.com.androidprojectstructure.model.ResponseData;
import rahul.com.androidprojectstructure.utility.UtilConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by Rahul Sadhu
 */
public abstract class CallbackResponse<T> implements Callback<T>, FinalResponseListener<T> {

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        Response<ResponseData> data = (Response<ResponseData>) response;

        if (response.isSuccessful()) {
            if(data.body().success == UtilConstants.SUCCESS){
                this.onSuccessResponse(call,data.body());
            }else {
                onFailureResponse(call,data.body());
            }

        } else {
            sendError(response.code(), response, call);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

        ResponseData responseData = new ResponseData();
        responseData.success = UtilConstants.FAILURE;


        if (t instanceof ConnectException) {
            responseData.message = "No Internet connection!";
        } else if (t instanceof SocketTimeoutException) {
            responseData.message = "Please try again later…";
        } else {
            responseData.message = "Unknown Error !";
        }

        this.onFailureResponse(call, responseData);

    }


    private void sendError(int code, Response<T> response, Call<T> call) {
        String message = "";

        switch (code) {
            case 404:
                message = "Not Found !";
                break;
            case 500:
                message = "Server Error !";
                break;
            default:
                message = "Unknown Error !";
                break;
        }

        ResponseData responseData = new ResponseData();
        responseData.success = UtilConstants.FAILURE;
        responseData.message = message;
        this.onFailureResponse(call, responseData);
    }
}
