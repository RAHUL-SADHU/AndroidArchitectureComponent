package rahul.com.androidprojectstructure.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahul Sadhu
 */

public class UserModel {
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("phonecode")
    @Expose
    private String phonecode;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("referral_code")
    @Expose
    private String referralCode;
    @SerializedName("otp_verified")
    @Expose
    private Integer otpVerified;
    @SerializedName("facebook_id")
    @Expose
    private String facebookId;
    @SerializedName("google_id")
    @Expose
    private String googleId;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("is_visible")
    @Expose
    private Integer isVisible;
    @SerializedName("food_type_id")
    @Expose
    private String foodTypeId;
    @SerializedName("preference_name")
    @Expose
    private String preferenceName;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("accesstoken")
    @Expose
    private String accesstoken;
    @SerializedName("isExistingUser")
    @Expose
    private Integer isExistingUser;
    @SerializedName("is_social_login")
    @Expose
    private Integer isSocialLogin;
    @SerializedName("profession")
    @Expose
    private String profession;
}

