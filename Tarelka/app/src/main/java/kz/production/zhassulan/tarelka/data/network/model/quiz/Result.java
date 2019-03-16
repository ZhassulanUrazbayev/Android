package kz.production.kuanysh.tarelka.data.network.model.quiz;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 29.06.2018.
 */
import java.util.List;

public class Result implements Parcelable
{

    @SerializedName("quizzes")
    @Expose
    private List<Quizzes> quizzes = null;
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
        in.readList(this.quizzes, (Quizzes.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param quizzes
     */
    public Result(List<Quizzes> quizzes) {
        super();
        this.quizzes = quizzes;
    }

    public List<Quizzes> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quizzes> quizzes) {
        this.quizzes = quizzes;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(quizzes);
    }

    public int describeContents() {
        return 0;
    }

}