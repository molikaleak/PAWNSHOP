package Pawn.Class;

public class Branch {

    private int branch_id;
    private String name;
    private String location;
    private int manager_id;

    public Branch(int branch_id, String name, String location, int manager_id) {
        this.branch_id = branch_id;
        this.name = name;
        this.location = location;
        this.manager_id = manager_id;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
}
