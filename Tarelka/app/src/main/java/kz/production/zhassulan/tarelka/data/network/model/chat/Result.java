package kz.production.kuanysh.tarelka.data.network.model.chat; ;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable
{

    @SerializedName("count_pages")
    @Expose
    private Integer countPages;
    @SerializedName("count_data")
    @Expose
    private Integer countData;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("next_page")
    @Expose
    private String nextPage;
    @SerializedName("prev_page")
    @Expose
    private Object prevPage;
    @SerializedName("chats")
    @Expose
    private List<Chat> chats = null;
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
        this.countPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.countData = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.offset = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.limit = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.currentPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.nextPage = ((String) in.readValue((String.class.getClassLoader())));
        this.prevPage = ((Object) in.readValue((Object.class.getClassLoader())));
        in.readList(this.chats, (Chat.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param limit
     * @param prevPage
     * @param nextPage
     * @param countData
     * @param countPages
     * @param currentPage
     * @param offset
     * @param chats
     */
    public Result(Integer countPages, Integer countData, Integer offset, Integer limit, Integer currentPage, String nextPage, Object prevPage, List<Chat> chats) {
        super();
        this.countPages = countPages;
        this.countData = countData;
        this.offset = offset;
        this.limit = limit;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
        this.prevPage = prevPage;
        this.chats = chats;
    }

    public Integer getCountPages() {
        return countPages;
    }

    public void setCountPages(Integer countPages) {
        this.countPages = countPages;
    }

    public Integer getCountData() {
        return countData;
    }

    public void setCountData(Integer countData) {
        this.countData = countData;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public Object getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(Object prevPage) {
        this.prevPage = prevPage;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(countPages);
        dest.writeValue(countData);
        dest.writeValue(offset);
        dest.writeValue(limit);
        dest.writeValue(currentPage);
        dest.writeValue(nextPage);
        dest.writeValue(prevPage);
        dest.writeList(chats);
    }

    public int describeContents() {
        return 0;
    }

}