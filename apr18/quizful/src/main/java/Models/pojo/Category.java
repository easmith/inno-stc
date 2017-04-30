package Models.pojo;

/**
 * Created by eku on 13.04.17.
 */


public class Category {

    private int id;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
