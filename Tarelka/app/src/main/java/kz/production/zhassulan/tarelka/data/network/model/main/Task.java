package kz.production.kuanysh.tarelka.data.network.model.main;

/**
 * Created by User on 20.07.2018.
 */
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("access")
    @Expose
    private Integer access;

    @SerializedName("status")
    @Expose
    private Integer status;

    public final static Parcelable.Creator<Task> CREATOR = new Creator<Task>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return (new Task[size]);
        }

    }
            ;

    protected Task(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.text = ((String) in.readValue((String.class.getClassLoader())));
        this.access = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Task() {
    }

    /**
     *
     * @param id
     * @param text
     * @param title
     * @param image
     * @param access
     */
    public Task(Integer id, String title, String image, String text, Integer access,Integer status) {
        super();
        this.id = id;
        this.title = title;
        this.image = image;
        this.text = text;
        this.access = access;
        this.status = status;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(image);
        dest.writeValue(text);
        dest.writeValue(access);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}