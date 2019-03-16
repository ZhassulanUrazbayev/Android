package kz.production.kuanysh.tarelka.data.network.model.quiz;

/**
 * Created by User on 21.07.2018.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quizzes implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("access")
    @Expose
    private Integer access;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("step")
    @Expose
    private Boolean step;
    @SerializedName("img_in7days")
    @Expose
    private Boolean imgIn7days;
    @SerializedName("7days")
    @Expose
    private Boolean _7days;
    @SerializedName("last_action_day")
    @Expose
    private String lastActionDay;
    public final static Parcelable.Creator<Quizzes> CREATOR = new Creator<Quizzes>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Quizzes createFromParcel(Parcel in) {
            return new Quizzes(in);
        }

        public Quizzes[] newArray(int size) {
            return (new Quizzes[size]);
        }

    }
            ;

    protected Quizzes(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.access = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.step = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.imgIn7days = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this._7days = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.lastActionDay = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Quizzes() {
    }

    /**
     *
     * @param imgIn7days
     * @param id
     * @param title
     * @param _7days
     * @param status
     * @param access
     * @param step
     * @param lastActionDay
     */
    public Quizzes(Integer id, String title, Integer access, Integer status, Boolean step, Boolean imgIn7days, Boolean _7days, String lastActionDay) {
        super();
        this.id = id;
        this.title = title;
        this.access = access;
        this.status = status;
        this.step = step;
        this.imgIn7days = imgIn7days;
        this._7days = _7days;
        this.lastActionDay = lastActionDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getStep() {
        return step;
    }

    public void setStep(Boolean step) {
        this.step = step;
    }

    public Boolean getImgIn7days() {
        return imgIn7days;
    }

    public void setImgIn7days(Boolean imgIn7days) {
        this.imgIn7days = imgIn7days;
    }

    public Boolean get7days() {
        return _7days;
    }

    public void set7days(Boolean _7days) {
        this._7days = _7days;
    }

    public String getLastActionDay() {
        return lastActionDay;
    }

    public void setLastActionDay(String lastActionDay) {
        this.lastActionDay = lastActionDay;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(access);
        dest.writeValue(status);
        dest.writeValue(step);
        dest.writeValue(imgIn7days);
        dest.writeValue(_7days);
        dest.writeValue(lastActionDay);
    }

    public int describeContents() {
        return 0;
    }

}