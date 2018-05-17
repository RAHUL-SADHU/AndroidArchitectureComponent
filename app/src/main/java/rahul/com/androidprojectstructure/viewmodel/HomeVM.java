package rahul.com.androidprojectstructure.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import rahul.com.androidprojectstructure.baseclass.BaseViewModel;
import rahul.com.androidprojectstructure.datasource.UserDataSource;
import rahul.com.androidprojectstructure.model.UserModel;
import rahul.com.androidprojectstructure.retrofit.NetworkState;

/**
 * Created by 16/5/18.
 */
public class HomeVM extends BaseViewModel {

    public LiveData<NetworkState> networkState = new MutableLiveData<NetworkState>();
    public UserDataSource userDataSource;
    private LiveData<PagedList<UserModel>> users;
    private MutableLiveData<UserDataSource> dataSourceLiveData = new MutableLiveData<>();
    private Executor executor;

    public void initPagination() {
        executor = Executors.newFixedThreadPool(5);

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .setEnablePlaceholders(false)
                .build();

        DataSource.Factory<Integer, UserModel> dataFactory = new android.arch.paging.DataSource.Factory<Integer, UserModel>() {
            @Override
            public android.arch.paging.DataSource<Integer, UserModel> create() {
                userDataSource = new UserDataSource(executor);
                dataSourceLiveData.postValue(userDataSource);
                return userDataSource;
            }
        };


        users = new LivePagedListBuilder(dataFactory, config).build();
        networkState = Transformations.switchMap(dataSourceLiveData, UserDataSource::getNetworkState);


    }

    public LiveData<PagedList<UserModel>> getUsers() {
        return users;
    }

}
