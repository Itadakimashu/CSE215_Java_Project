package Users;

import java.io.Serializable;

public class Ride implements Serializable{
    String fromLocation;
    String toLocation;
    Customer customer;
    Rider rider;
    double fare;
    String progress;
    public Ride(Customer customer, Rider rider,String fromLocation, String toLocation) {
        this.customer = customer;
        this.rider = rider;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        fare = 180.0;
        this.progress = "Ongoing";
    }
    public void update(String progress) {
       this.progress = progress;
    }
    
}
