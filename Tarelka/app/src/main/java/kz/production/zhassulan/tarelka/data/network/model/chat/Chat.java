package kz.production.kuanysh.tarelka.data.network.model.chat; ;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chat implements Parcelable
{

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
    private String message;
    @SerializedName("readed")
    @Expose
    private Integer readed;
    @SerializedName("deleted")
    @Expose
    private Integer deleted;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("image")
    @Expose
    private List<String> image = null;
    public final static Parcelable.Creator<Chat> CREATOR = new Creator<Chat>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Chat createFromParcel(Parcel in) {
            return new Chat(in);
        }

        public Chat[] newArray(int size) {
            return (new Chat[size]);
        }

    }
            ;

    protected Chat(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.from = ((String) in.readValue((String.class.getClassLoader())));
        this.to = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.readed = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.deleted = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.image, (java.lang.String.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Chat() {
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
    public Chat(Integer id, String from, String to, String message, Integer readed, Integer deleted, String createdAt, List<String> image) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.readed = readed;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.image = image;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(from);
        dest.writeValue(to);
        dest.writeValue(message);
        dest.writeValue(readed);
        dest.writeValue(deleted);
        dest.writeValue(createdAt);
        dest.writeList(image);
    }

    public int describeContents() {
        return 0;
    }

}