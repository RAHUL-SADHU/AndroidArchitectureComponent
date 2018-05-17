package rahul.com.androidprojectstructure.utility;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import rahul.com.androidprojectstructure.R;

/**
 * Created by 16/5/18.
 */
public class BindingUtils {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).setDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.logo)).load(url).into(view);
    }

}
