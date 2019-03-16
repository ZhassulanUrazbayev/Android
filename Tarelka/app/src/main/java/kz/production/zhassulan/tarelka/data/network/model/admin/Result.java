package kz.production.kuanysh.tarelka.data.network.model.admin;

/**
 * Created by User on 23.07.2018.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable
{

    @SerializedName("whatsapp")
    @Expose
    private String whatsapp;
    @SerializedName("telegram")
    @Expose
    private String telegram;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("viber")
    @Expose
    private String viber;
    @SerializedName("vk")
    @Expose
    private String vk;
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
        this.whatsapp = ((String) in.readValue((String.class.getClassLoader())));
        this.telegram = ((String) in.readValue((String.class.getClassLoader())));
        this.facebook = ((String) in.readValue((String.class.getClassLoader())));
        this.viber = ((String) in.readValue((String.class.getClassLoader())));
        this.vk = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param viber
     * @param vk
     * @param facebook
     * @param telegram
     * @param whatsapp
     */
    public Result(String whatsapp, String telegram, String facebook, String viber, String vk) {
        super();
        this.whatsapp = whatsapp;
        this.telegram = telegram;
        this.facebook = facebook;
        this.viber = viber;
        this.vk = vk;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getViber() {
        return viber;
    }

    public void setViber(String viber) {
        this.viber = viber;
    }

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(whatsapp);
        dest.writeValue(telegram);
        dest.writeValue(facebook);
        dest.writeValue(viber);
        dest.writeValue(vk);
    }

    public int describeContents() {
        return 0;
    }

}
