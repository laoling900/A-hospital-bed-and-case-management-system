package model;

public  class Staff {
    private int id;
    private String name;
    private String password;
    private String position;


    public Staff(int id, String name, String password ,String position) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.position = position;
    }

    public Staff() {

    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
