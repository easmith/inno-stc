package models.POJO;

/**
 * Created by eku on 19.04.17.
 */
public class Group {
    private int id;

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
