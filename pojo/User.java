package pre.cg.cgandroid.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class User {
    private String name;
    private int imageId;

    public User(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
