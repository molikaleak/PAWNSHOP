package Pawn.Class;

public class Currency_branch_emp {

    public int current_branch_emp_id;
    public int branch_id;
    public int emp_id;

    public Currency_branch_emp(int current_branch_emp_id, int branch_id, int emp_id) {
        this.current_branch_emp_id = current_branch_emp_id;
        this.branch_id = branch_id;
        this.emp_id = emp_id;
    }

    public int getCurrent_branch_emp_id() {
        return current_branch_emp_id;
    }

    public void setCurrent_branch_emp_id(int current_branch_emp_id) {
        this.current_branch_emp_id = current_branch_emp_id;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }
}
