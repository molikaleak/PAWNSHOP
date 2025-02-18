package Pawn.Class;

public class Employee {
    private String emp_id;
    private String branch_id;
    private String name;
    private String phone_number;
    private String address;
    private String password;

    public Employee(String emp_id, String branch_id, String name, String phone_number, String address, String password) {
        this.emp_id = emp_id;
        this.branch_id = branch_id;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_id='" + emp_id + '\'' +
                ", branch_id='" + branch_id + '\'' +
                ", name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    // Getters and setters
    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}