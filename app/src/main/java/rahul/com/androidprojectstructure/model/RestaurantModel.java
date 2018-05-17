package rahul.com.androidprojectstructure.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 24/11/17.
 */

public class RestaurantModel {
    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("business_name")
    @Expose
    private String businessName;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone_number")
    @Expose
    private String phone_number;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("imageoriginal")
    @Expose
    private String imageoriginal;
    @SerializedName("imagemedium")
    @Expose
    private String imagemedium;
    @SerializedName("imagesmall")
    @Expose
    private String imagesmall;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("sitting_type")
    @Expose
    private String sittingType;
    @SerializedName("is_alcoholic")
    @Expose
    private String isAlcoholic;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("day")
    @Expose
    private String day;

    @SerializedName("ratings")
    @Expose
    private String rating;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getImageoriginal() {
        return imageoriginal;
    }

    public void setImageoriginal(String imageoriginal) {
        this.imageoriginal = imageoriginal;
    }

    public String getImagemedium() {
        return imagemedium;
    }

    public void setImagemedium(String imagemedium) {
        this.imagemedium = imagemedium;
    }

    public String getImagesmall() {
        return imagesmall;
    }

    public void setImagesmall(String imagesmall) {
        this.imagesmall = imagesmall;
    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSittingType() {
        return sittingType;
    }

    public void setSittingType(String sittingType) {
        this.sittingType = sittingType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getIsAlcoholic() {
        return isAlcoholic;
    }

    public void setIsAlcoholic(String isAlcoholic) {
        this.isAlcoholic = isAlcoholic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
