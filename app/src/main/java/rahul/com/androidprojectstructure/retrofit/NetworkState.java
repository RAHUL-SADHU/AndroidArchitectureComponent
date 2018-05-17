package rahul.com.androidprojectstructure.retrofit;

/**
 * Created by brijesh on 25/12/17.
 */


public class NetworkState {
    public static final NetworkState LOADED;
    public static final NetworkState LOADING;

    static {
        LOADED = new NetworkState(Status.SUCCESS, "Success");
        LOADING = new NetworkState(Status.RUNNING, "Running");
    }

    private final Status status;
    private final String msg;

    public NetworkState(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }


}
