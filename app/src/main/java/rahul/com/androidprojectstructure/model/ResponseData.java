package rahul.com.androidprojectstructure.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahul Sadhu
 */

public class ResponseData<T> {

    @SerializedName("MESSAGE")
    public String message;
    @SerializedName("SUCCESS")
    public int success;
    @SerializedName("DATA")
    public T data;
    @SerializedName("STATUS")
    public String status;
    public String key;
}
