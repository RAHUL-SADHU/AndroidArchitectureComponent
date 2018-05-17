package rahul.com.androidprojectstructure.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by 18/1/18.
 */

public class PaginationModel<T> {

    @SerializedName("data")
    @Expose
    public ArrayList<T> data = null;
    @SerializedName("total")
    @Expose
    public int total;
}
