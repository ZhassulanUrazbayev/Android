package kz.production.kuanysh.tarelka.data.network.model.sendmeal;

/**
 * Created by User on 17.07.2018.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("fio")
    @Expose
    private String fio;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("goals")
    @Expose
    private List<String> goals = null;
    @SerializedName("meals")
    @Expose
    private List<String> meals = null;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
            ;

    protected Result(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.avatar = ((String) in.readValue((String.class.getClassLoader())));
        this.fio = ((String) in.readValue((String.class.getClassLoader())));
        this.weight = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.age = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.height = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.goals, (java.lang.String.class.getClassLoader()));
        in.readList(this.meals, (java.lang.String.class.getClassLoader()));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param phone
     * @param weight
     * @param status
     * @param avatar
     * @param fio
     * @param updatedAt
     * @param id
     * @param goals
     * @param height
     * @param meals
     * @param token
     * @param createdAt
     * @param age
     */
    public Result(Integer id, String token, String phone, String status, String avatar, String fio, Integer weight, Integer age, Integer height, List<String> goals, List<String> meals, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.token = token;
        this.phone = phone;
        this.status = status;
        this.avatar = avatar;
        this.fio = fio;
        this.weight = weight;
        this.age = age;
        this.height = height;
        this.goals = goals;
        this.meals = meals;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<String> getGoals() {
        return goals;
    }

    public void setGoals(List<String> goals) {
        this.goals = goals;
    }

    public List<String> getMeals() {
        return meals;
    }

    public void setMeals(List<String> meals) {
        this.meals = meals;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(token);
        dest.writeValue(phone);
        dest.writeValue(status);
        dest.writeValue(avatar);
        dest.writeValue(fio);
        dest.writeValue(weight);
        dest.writeValue(age);
        dest.writeValue(height);
        dest.writeList(goals);
        dest.writeList(meals);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return 0;
    }

}