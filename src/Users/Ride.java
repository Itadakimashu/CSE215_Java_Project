package Users;

import java.io.Serializable;

public class Ride implements Serializable{
    public String fromLocation;
    public String toLocation;
    public Customer customer;
    public Rider rider;
    public double fare;
    public String progress;

    private static double fare_matrix[][] = {
        {0, 25, 55, 80, 100},
        {25, 0, 30, 40, 50},
        {55, 30, 0, 20, 40},
        {80, 40, 20, 0, 30},
        {100, 50, 40, 30, 0}
    };

    public Ride(Customer customer, Rider rider,String fromLocation, String toLocation) {
        this.customer = customer;
        this.rider = rider;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        fare = calculate_fare(fromLocation,toLocation);
        this.progress = "Ongoing";
    }
    public void update(String progress) {
       this.progress = progress;
    }

    public double calculate_fare(String fromLocation, String toLocation){
        int index[] = new int[2];
        index[0] = getIndex(fromLocation);
        index[1] = getIndex(toLocation);
        return fare_matrix[index[0]][index[1]]; 
    }
    private int getIndex(String location) {
        switch(location.toLowerCase()){
            case "gaza": return 0;
            case "west bank": return 1;
            case "rafah": return 2;
            case "al aqsa": return 3;
            case "haifa": return 4;
            default:
            return -1;
        }

    }
    
}
