package kz.production.kuanysh.tarelka.data.prefs;

/**
 * Created by User on 01.08.2018.
 */

public class Board {
    private String description;
    private int image;


    public Board(String  description, int image) {
        this.description = description;
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
