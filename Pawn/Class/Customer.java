package Pawn.Class;

public class Customer {

    public int customer_id;
    private String name;
    private String phone_number;
    private String contact_method;

    public Customer(int customer_id, String name, String phone_number, String contact_method) {
        this.customer_id = customer_id;
        this.name = name;
        this.phone_number = phone_number;
        this.contact_method = contact_method;
    }
    public Customer( String name , String phone_number , String contact_method){
        this.name = name;
        this.phone_number = phone_number;
        this.contact_method = contact_method ;
    }
    //constructor for create new contract
    public Customer(String customerName, String phoneNumberPlaceholder, double loanAmount, int duration, double nextRepaymentAmount, String startDateStr, String nextRepaymentDueString, int loanTypeIndex) {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phone_number + '\'' +
                ", contactMethod='" + contact_method + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getContact_method() {
        return contact_method;
    }

    public void setContact_method(String contact_method) {
        this.contact_method = contact_method;
    }

    public Object getPhoneNumber() {
        return phone_number;
    }
}
