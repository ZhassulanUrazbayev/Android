package kz.production.kuanysh.tarelka.data.network.model.goal;

/**
 * Created by User on 17.07.2018.
 */
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendGoal implements Parcelable
{

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private Result result;
    public final static Parcelable.Creator<SendGoal> CREATOR = new Creator<SendGoal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SendGoal createFromParcel(Parcel in) {
            return new SendGoal(in);
        }

        public SendGoal[] newArray(int size) {
            return (new SendGoal[size]);
        }

    }
            ;

    protected SendGoal(Parcel in) {
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.result = ((Result) in.readValue((Result.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public SendGoal() {
    }

    /**
     *
     * @param message
     * @param statusCode
     * @param result
     */
    public SendGoal(Integer statusCode, String message, Result result) {
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
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