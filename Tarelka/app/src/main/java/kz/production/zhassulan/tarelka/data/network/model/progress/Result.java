package kz.production.kuanysh.tarelka.data.network.model.progress;

/**
 * Created by User on 17.07.2018.
 */


import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable
{

    @SerializedName("perc")
    @Expose
    private List<Perc> perc = null;
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
        in.readList(this.perc, (Perc.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param perc
     */
    public Result(List<Perc> perc) {
        super();
        this.perc = perc;
    }

    public List<Perc> getPerc() {
        return perc;
    }

    public void setPerc(List<Perc> perc) {
        this.perc = perc;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(perc);
    }

    public int describeContents() {
        return 0;
    }

}