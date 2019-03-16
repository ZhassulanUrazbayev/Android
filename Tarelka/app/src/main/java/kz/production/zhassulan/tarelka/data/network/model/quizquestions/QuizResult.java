package kz.production.kuanysh.tarelka.data.network.model.quizquestions; ;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizResult implements Parcelable
{

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<QuizResult> CREATOR = new Creator<QuizResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public QuizResult createFromParcel(Parcel in) {
            return new QuizResult(in);
        }

        public QuizResult[] newArray(int size) {
            return (new QuizResult[size]);
        }

    }
            ;

    protected QuizResult(Parcel in) {
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public QuizResult() {
    }

    /**
     *
     * @param message
     * @param statusCode
     */
    public QuizResult(Integer statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(statusCode);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}

