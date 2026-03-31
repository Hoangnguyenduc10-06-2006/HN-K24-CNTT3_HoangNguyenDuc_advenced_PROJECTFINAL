package model;

public class Order {
    private int user_id;
    private double total_amount;
    private String status ;

    public Order(int user_id, double total_amount, String status) {
        this.user_id = user_id;
        this.total_amount = total_amount;
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
