package kz.production.kuanysh.tarelka.data.network.model.chat.receive; ;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("message")
    @Expose
    private Object message=null;
    @SerializedName("image")
    @Expose
    private List<String> image = null;
    @SerializedName("readed")
    @Expose
    private Integer readed;
    @SerializedName("deleted")
    @Expose
    private Integer deleted;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
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
        this.from = ((String) in.readValue((String.class.getClassLoader())));
        this.to = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((Object) in.readValue((Object.class.getClassLoader())));
        in.readList(this.image, (java.lang.String.class.getClassLoader()));
        this.readed = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.deleted = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param message
     * @param to
     * @param id
     * @param readed
     * @param createdAt
     * @param image
     * @param from
     * @param deleted
     */
    public Result(Integer id, String from, String to, Object message, List<String> image, Integer readed, Integer deleted, String createdAt) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.image = image;
        this.readed = readed;
        this.deleted = deleted;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public Integer getReaded() {
        return readed;
    }

    public void setReaded(Integer readed) {
        this.readed = readed;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(from);
        dest.writeValue(to);
        dest.writeValue(message);
        dest.writeList(image);
        dest.writeValue(readed);
        dest.writeValue(deleted);
        dest.writeValue(createdAt);
    }

    public int describeContents() {
        return 0;
    }

}