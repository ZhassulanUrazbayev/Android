package kz.production.kuanysh.tarelka.data.network.model.progress;

/**
 * Created by User on 20.07.2018.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Perc implements Parcelable
{

    @SerializedName("correct")
    @Expose
    private String correct;
    @SerializedName("max")
    @Expose
    private String max;
    @SerializedName("percentage")
    @Expose
    private Double percentage;
    @SerializedName("date")
    @Expose
    private String date;
    public final static Parcelable.Creator<Perc> CREATOR = new Creator<Perc>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Perc createFromParcel(Parcel in) {
            return new Perc(in);
        }

        public Perc[] newArray(int size) {
            return (new Perc[size]);
        }

    }
            ;

    protected Perc(Parcel in) {
        this.correct = ((String) in.readValue((String.class.getClassLoader())));
        this.max = ((String) in.readValue((String.class.getClassLoader())));
        this.percentage = ((Double) in.readValue((Integer.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Perc() {
    }

    /**
     *
     * @param max
     * @param correct
     * @param percentage
     * @param date
     */
    public Perc(String correct, String max, Double percentage, String date) {
        super();
        this.correct = correct;
        this.max = max;
        this.percentage = percentage;
        this.date = date;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(correct);
        dest.writeValue(max);
        dest.writeValue(percentage);
        dest.writeValue(date);
    }

    public int describeContents() {
        return 0;
    }

}
