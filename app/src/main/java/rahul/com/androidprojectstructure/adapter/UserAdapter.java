package rahul.com.androidprojectstructure.adapter;

import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rahul.com.androidprojectstructure.R;
import rahul.com.androidprojectstructure.databinding.NetworkStateItemBinding;
import rahul.com.androidprojectstructure.databinding.UserItemBinding;
import rahul.com.androidprojectstructure.interfaces.ListItemClickListener;
import rahul.com.androidprojectstructure.model.UserModel;
import rahul.com.androidprojectstructure.retrofit.NetworkState;

/**
 * Created by 16/5/18.
 */
public class UserAdapter extends PagedListAdapter<UserModel, UserAdapter.HomeItemHolder> {

    private NetworkState networkState;
    private ListItemClickListener itemClickListener;

    public UserAdapter(ListItemClickListener itemClickListener) {
        super(UserModel.diffCallBack);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public HomeItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = null;
        if (viewType == R.layout.user_item) {
            view = layoutInflater.inflate(R.layout.user_item, parent, false);
        } else if (viewType == R.layout.network_state_item) {
            view = layoutInflater.inflate(R.layout.network_state_item, parent, false);
        }

        return new HomeItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemHolder holder, int position) {
        if (holder.itemBinding instanceof UserItemBinding) {
            ((UserItemBinding) holder.itemBinding).setUserModel(getItem(position));

        } else if (holder.itemBinding instanceof NetworkStateItemBinding) {

            ((NetworkStateItemBinding) holder.itemBinding).setNetworkState(networkState);
            ((NetworkStateItemBinding) holder.itemBinding).retryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onRetryClick(v, position);
                }
            });

        }

        holder.itemBinding.executePendingBindings();
    }


    private boolean hasExtraRow() {
        return networkState != null && networkState != NetworkState.LOADED;
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.network_state_item;
        } else {
            return R.layout.user_item;
        }
    }

    class HomeItemHolder extends RecyclerView.ViewHolder {

        ViewDataBinding itemBinding;

        HomeItemHolder(View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);
        }
    }

}
