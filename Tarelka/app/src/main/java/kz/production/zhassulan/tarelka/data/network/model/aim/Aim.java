package kz.production.kuanysh.tarelka.data.network.model.aim;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Aim implements Parcelable
{

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;
    public final static Parcelable.Creator<Aim> CREATOR = new Creator<Aim>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Aim createFromParcel(Parcel in) {
            return new Aim(in);
        }

        public Aim[] newArray(int size) {
            return (new Aim[size]);
        }

    }
            ;

    protected Aim(Parcel in) {
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.result, (Result.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Aim() {
    }

    /**
     *
     * @param message
     * @param statusCode
     * @param result
     */
    public Aim(Integer statusCode, String message, List<Result> result) {
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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(statusCode);
        dest.writeValue(message);
        dest.writeList(result);
    }

    public int describeContents() {
        return 0;
    }

}