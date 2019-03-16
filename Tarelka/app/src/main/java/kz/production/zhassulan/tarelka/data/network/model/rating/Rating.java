package kz.production.kuanysh.tarelka.data.network.model.rating;

/**
 * Created by User on 08.08.2018.
 */
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating implements Parcelable
{

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private Integer result;
    public final static Parcelable.Creator<Rating> CREATOR = new Creator<Rating>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Rating createFromParcel(Parcel in) {
            return new Rating(in);
        }

        public Rating[] newArray(int size) {
            return (new Rating[size]);
        }

    }
            ;

    protected Rating(Parcel in) {
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.result = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Rating() {
    }

    /**
     *
     * @param message
     * @param statusCode
     * @param result
     */
    public Rating(Integer statusCode, String message, Integer result) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(statusCode);
        dest.writeValue(message);
        dest.writeValue(result);
    }

    public int describeContents() {
        return 0;
    }

}